package com.springboot.wms.restapi.restapi.Sku;

import lombok.Data;

@Data
public class SkuDto {

    private long id;
    private String name;
    private String description;
    private boolean is_expired;

}
