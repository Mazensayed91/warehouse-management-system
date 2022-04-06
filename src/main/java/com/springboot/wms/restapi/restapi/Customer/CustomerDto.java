package com.springboot.wms.restapi.restapi.Customer;

import lombok.Data;

@Data
public class CustomerDto {

    private long id;
    private String first_name;
    private String last_name;
    private String address;
    private String number;
}
