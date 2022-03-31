package com.springboot.wms.restapi.restapi.Employee;

import lombok.Data;

@Data
public class EmployeeDto {

    private long id;
    private String first_name;
    private String last_name;
    private String address;
    private String number;
}
