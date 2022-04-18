package com.springboot.wms.restapi.restapi.AdviceLineLoadUnit;

import lombok.Data;

import java.util.Date;

@Data
public class AdviceLineLoadUnitDto {

    private long id;
    private long load_unit_id;
    private long advice_line_id;
    private int quantity_available;
}

