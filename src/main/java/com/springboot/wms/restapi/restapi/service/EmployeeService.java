package com.springboot.wms.restapi.restapi.service;

import com.springboot.wms.restapi.restapi.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getEmployees();
}
