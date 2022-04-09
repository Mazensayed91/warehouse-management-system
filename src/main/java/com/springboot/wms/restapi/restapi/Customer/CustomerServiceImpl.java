package com.springboot.wms.restapi.restapi.Customer;

import com.springboot.wms.restapi.restapi.ContactPerson.ContactPerson;
import com.springboot.wms.restapi.restapi.Employee.Employee;
import com.springboot.wms.restapi.restapi.Employee.EmployeeDto;
import com.springboot.wms.restapi.restapi.Employee.EmployeeRepository;
import com.springboot.wms.restapi.restapi.Employee.EmployeeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto){
        // convert DTO to entity
        Customer customer = mapToEntity(customerDto);

        Customer newCustomer = customerRepository.save(customer);

        // convert saved entity to DTO
        CustomerDto customerResponse;

        customerResponse = mapToDto(newCustomer);

        return customerResponse;
    }

    @Override
    public List<CustomerDto> getCustomers(){
        return customerRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private CustomerDto mapToDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());

        return customerDto;
    }

    private Customer mapToEntity(CustomerDto customerDto){

        return new Customer();
    }
}
