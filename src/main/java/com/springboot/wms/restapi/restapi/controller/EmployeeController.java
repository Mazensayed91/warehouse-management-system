package com.springboot.wms.restapi.restapi.controller;

import com.springboot.wms.restapi.restapi.dto.EmployeeDto;
import com.springboot.wms.restapi.restapi.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // create employee
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    // get employees
    @GetMapping
    public List<EmployeeDto> getEmployees(){
        return employeeService.getEmployees();
    }
}
