package com.springboot.wms.restapi.restapi.LoadUnitTypeSkuQu;

import com.springboot.wms.restapi.restapi.Advice.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LoadUnitTypeSkuQuRepository extends JpaRepository<LoadUnitTypeSkuQu, Long> {

    LoadUnitTypeSkuQu findBySkuQuantityUnitIdAndLoadUnitTypeId(Long sku_quantity_id, Long load_unit_type_id);

}
