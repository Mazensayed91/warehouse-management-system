package com.springboot.wms.restapi.restapi.LoadUnitTypeSkuQu;

import lombok.Data;

@Data
public class LoadUnitTypeSkuQuDto {

    private long id;
    private int quantity;
    private String orientation;
    private long sku_quantity_unit_id;
    private long load_unit_type_id;
}
