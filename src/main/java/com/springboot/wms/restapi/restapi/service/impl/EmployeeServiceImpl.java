package com.springboot.wms.restapi.restapi.service.impl;

import com.springboot.wms.restapi.restapi.dto.EmployeeDto;
import com.springboot.wms.restapi.restapi.model.ContactPerson;
import com.springboot.wms.restapi.restapi.model.Employee;
import com.springboot.wms.restapi.restapi.repository.EmployeeRepository;
import com.springboot.wms.restapi.restapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
        employeeDto.setFirstName(employee.getContactPerson().getFirstName());
        employeeDto.setLastName(employee.getContactPerson().getLastName());
        employeeDto.setNumber(employee.getContactPerson().getNumber());
        employeeDto.setAddress(employee.getContactPerson().getAddress());

        return employeeDto;
    }

    private Employee mapToEntity(EmployeeDto employeeDto){

        Employee employee = new Employee();
        ContactPerson contactPerson = new ContactPerson() ;
        contactPerson.setFirstName(employeeDto.getFirstName());
        contactPerson.setLastName(employeeDto.getLastName());
        contactPerson.setAddress(employeeDto.getAddress());
        contactPerson.setNumber(employeeDto.getNumber());

        employee.setContactPerson(contactPerson);

        return employee;
    }
}
