package com.springboot.wms.restapi.restapi.service;

import com.springboot.wms.restapi.restapi.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
}
