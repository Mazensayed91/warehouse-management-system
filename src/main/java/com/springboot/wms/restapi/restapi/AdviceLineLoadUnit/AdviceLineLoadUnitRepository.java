package com.springboot.wms.restapi.restapi.AdviceLineLoadUnit;

import com.springboot.wms.restapi.restapi.AdviceLine.AdviceLine;
import com.springboot.wms.restapi.restapi.LoadUnitTypeSkuQu.LoadUnitTypeSkuQu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AdviceLineLoadUnitRepository extends JpaRepository<AdviceLineLoadUnit, Long> {

    AdviceLineLoadUnit findByAdviceLineIdAndLoadUnitId(Long advice_line_id, Long load_unit_id);
}
