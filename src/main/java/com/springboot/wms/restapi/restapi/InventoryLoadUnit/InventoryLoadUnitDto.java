package com.springboot.wms.restapi.restapi.InventoryLoadUnit;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InventoryLoadUnitDto {

    private long id;
    private long load_unit_id;
    private long inventory_id;
    private BigDecimal price;

}

