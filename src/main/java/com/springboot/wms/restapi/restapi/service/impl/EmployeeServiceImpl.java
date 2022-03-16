package com.springboot.wms.restapi.restapi.service.impl;

import com.springboot.wms.restapi.restapi.dto.EmployeeDto;
import com.springboot.wms.restapi.restapi.model.Employee;
import com.springboot.wms.restapi.restapi.repository.EmployeeRepository;
import com.springboot.wms.restapi.restapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        Employee employee = new Employee();

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setNumber(employeeDto.getNumber());
        employee.setAddress(employeeDto.getAddress());

        Employee newEmployee = employeeRepository.save(employee);

        // convert saved entity to DTO
        EmployeeDto employeeResponse = new EmployeeDto();

        employeeResponse.setId(newEmployee.getId());
        employeeResponse.setFirstName(newEmployee.getFirstName());
        employeeResponse.setLastName(newEmployee.getLastName());
        employeeResponse.setNumber(newEmployee.getNumber());
        employeeResponse.setAddress(newEmployee.getAddress());

        return employeeResponse;
    }
}
