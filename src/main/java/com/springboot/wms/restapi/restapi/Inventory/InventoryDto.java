package com.springboot.wms.restapi.restapi.Inventory;

import lombok.Data;

import java.util.Date;

@Data
public class InventoryDto {

    private long id;
    private long sku_quantity_unit_id;
    private Date expire_date;

}

