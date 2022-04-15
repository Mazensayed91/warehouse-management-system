package com.springboot.wms.restapi.restapi.SkuQuantityUnit;

import lombok.Data;

@Data
public class SkuQuantityUnitDto {

    private long id;
    private long sku_id;
    private double height;
    private double width;
    private double length;
    private int singular_quantity;
    private String name;
}
