package com.springboot.wms.restapi.restapi.service;

import com.springboot.wms.restapi.restapi.Employee.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getEmployees();
}
