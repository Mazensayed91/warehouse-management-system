package com.springboot.wms.restapi.restapi.dto;

import lombok.Data;

@Data
public class EmployeeDto {

    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String number;
}
