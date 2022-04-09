package com.springboot.wms.restapi.restapi.User;

import com.springboot.wms.restapi.restapi.ContactPerson.ContactPerson;
import com.springboot.wms.restapi.restapi.Customer.CustomerController;
import com.springboot.wms.restapi.restapi.Customer.CustomerDto;
import com.springboot.wms.restapi.restapi.Employee.EmployeeController;
import com.springboot.wms.restapi.restapi.Employee.EmployeeDto;
import com.springboot.wms.restapi.restapi.Role.Role;
import com.springboot.wms.restapi.restapi.Role.RoleRepository;
import com.springboot.wms.restapi.restapi.Supplier.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SupplierController supplierController;

    @Autowired
    private CustomerController customerController;

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =  userRepository.findByContactPerson_Email(email);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getTitle()));
        });

        if(user != null){
            return new org.springframework.security.core.userdetails.User(user.getContactPerson().getEmail(),
                    user.getContactPerson().getPassword(),
                    authorities
            );
        }else{
            throw new UsernameNotFoundException("User email isn't valid");
        }
    }

    private enum ROLES_WITH_TABLES {
        EMPLOYEE, CUSTOMER, SUPPLIER;
    }

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           SupplierController supplierController, CustomerController customerController,
                           EmployeeController employeeController){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.supplierController = supplierController;
        this.customerController = customerController;
        this.employeeController = employeeController;

    }

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto){
        // convert DTO to entity
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = mapToEntity(userDto);

        // Retrieve all user roles
        Set<Role> roles_ids  = roleRepository.findByRoleIds(userDto.getRoles_ids());

        user.setRoles(roles_ids);

        User newUser = userRepository.save(user);

        // convert saved entity to DTO
        UserDto userResponse;

        userResponse = mapToDto(newUser);

        // add record in different user table based on role
        user.getRoles().forEach((role) -> {
            if(role.getTitle().equals(ROLES_WITH_TABLES.SUPPLIER.name())) {
                SupplierDto supplierDto = new SupplierDto();
                supplierDto.setId(user.getId());
                supplierController.createSupplier(supplierDto);
            }else if(role.getTitle().equals(ROLES_WITH_TABLES.CUSTOMER.name())){
                CustomerDto customerDto = new CustomerDto();
                customerDto.setId(user.getId());
                customerController.createCustomer(customerDto);
            }else if(role.getTitle().equals(ROLES_WITH_TABLES.EMPLOYEE.name())){
                EmployeeDto employeeDto = new EmployeeDto();
                employeeDto.setId(user.getId());
                employeeController.createEmployee(employeeDto);
            }
        });

        return userResponse;
    }

    @Override
    public List<UserDto> getUsers(){
        return userRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private UserDto mapToDto(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirst_name(user.getContactPerson().getFirst_name());
        userDto.setLast_name(user.getContactPerson().getLast_name());
        userDto.setNumber(user.getContactPerson().getNumber());
        userDto.setAddress(user.getContactPerson().getAddress());
        userDto.setPassword(user.getContactPerson().getPassword());
        userDto.setEmail(user.getContactPerson().getEmail());
        userDto.setRoles_ids(user.getRoles().stream().map(Role::getId).collect(Collectors.toSet()));

        return userDto;
    }

    private User mapToEntity(UserDto userDto){

        User user = new User();
        ContactPerson contactPerson = new ContactPerson() ;
        contactPerson.setFirst_name(userDto.getFirst_name());
        contactPerson.setLast_name(userDto.getLast_name());
        contactPerson.setAddress(userDto.getAddress());
        contactPerson.setNumber(userDto.getNumber());
        contactPerson.setPassword(userDto.getPassword());
        contactPerson.setEmail(userDto.getEmail());

        user.setContactPerson(contactPerson);

        return user;
    }
}
