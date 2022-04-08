package com.springboot.wms.restapi.restapi.Sku;

import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SkuRepository extends JpaRepository<Sku, Long> {
}
