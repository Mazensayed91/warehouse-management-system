package com.springboot.wms.restapi.restapi.Employee;

import com.springboot.wms.restapi.restapi.Employee.EmployeeDto;
import com.springboot.wms.restapi.restapi.ContactPerson.ContactPerson;
import com.springboot.wms.restapi.restapi.Employee.Employee;
import com.springboot.wms.restapi.restapi.Employee.EmployeeRepository;
import com.springboot.wms.restapi.restapi.Employee.EmployeeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        // convert DTO to entity
        Employee employee = mapToEntity(employeeDto);

        Employee newEmployee = employeeRepository.save(employee);

        // convert saved entity to DTO
        EmployeeDto employeeResponse;

        employeeResponse = mapToDto(newEmployee);

        return employeeResponse;
    }

    @Override
    public List<EmployeeDto> getEmployees(){
        return employeeRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private EmployeeDto mapToDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(employee.getId());
        employeeDto.setFirst_name(employee.getContact_person().getFirst_name());
        employeeDto.setLast_name(employee.getContact_person().getLast_name());
        employeeDto.setNumber(employee.getContact_person().getNumber());
        employeeDto.setAddress(employee.getContact_person().getAddress());

        return employeeDto;
    }

    private Employee mapToEntity(EmployeeDto employeeDto){

        Employee employee = new Employee();
        ContactPerson contactPerson = new ContactPerson() ;
        contactPerson.setFirst_name(employeeDto.getFirst_name());
        contactPerson.setLast_name(employeeDto.getLast_name());
        contactPerson.setAddress(employeeDto.getAddress());
        contactPerson.setNumber(employeeDto.getNumber());

        employee.setContact_person(contactPerson);

        return employee;
    }
}
