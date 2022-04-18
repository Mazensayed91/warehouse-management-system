package com.springboot.wms.restapi.restapi.Inventory;

import com.springboot.wms.restapi.restapi.AdviceLine.AdviceLine;
import com.springboot.wms.restapi.restapi.AdviceLineLoadUnit.AdviceLineLoadUnit;
import com.springboot.wms.restapi.restapi.SkuQuantityUnit.SkuQuantityUnit;
import com.springboot.wms.restapi.restapi.Supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByExpireDateAndSupplierAndSkuQuantityUnit(Date expire_date, Supplier supplier, SkuQuantityUnit skuQuantityUnit);

}
