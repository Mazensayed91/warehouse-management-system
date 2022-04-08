package com.springboot.wms.restapi.restapi.Advice;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AdviceDto {

    private long id;
    private BigDecimal sub_total;
    private BigDecimal discount;
    private int quantity;
    private long supplier_id;
    private long employee_id;
}

