package com.springboot.wms.restapi.restapi.LoadUnitType;

import lombok.Data;

@Data
public class LoadUnitTypeDto {

    private long id;
    private String name;
    private double height;
    private double width;
    private double length;
}
