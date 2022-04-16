package com.springboot.wms.restapi.restapi.AdviceLine;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AdviceLineDto {

    private long id;
    private long sku_quantity_unit_id;
    private double unit_price;
    private int quantity;
    private Date expire_date;
}

