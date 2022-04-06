package com.springboot.wms.restapi.restapi.Supplier;

import lombok.Data;

@Data
public class SupplierDto {

    private long id;
    private String first_name;
    private String last_name;
    private String address;
    private String number;
}
