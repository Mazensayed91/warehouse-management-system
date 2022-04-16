package com.springboot.wms.restapi.restapi.Advice;

import com.springboot.wms.restapi.restapi.AdviceLine.AdviceLineDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class AdviceDto {

    private long id;
    private BigDecimal sub_total;
    private BigDecimal discount;
    private int quantity;
    private long supplier_id;
    private long employee_id;
    Set<AdviceLineDto> advice_line_dtos;
}

