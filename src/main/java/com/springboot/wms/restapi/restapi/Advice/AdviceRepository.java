package com.springboot.wms.restapi.restapi.Advice;

import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdviceRepository extends JpaRepository<Advice, Long> {
}
