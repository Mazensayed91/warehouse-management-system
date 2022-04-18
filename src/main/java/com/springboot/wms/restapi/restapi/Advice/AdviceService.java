package com.springboot.wms.restapi.restapi.Advice;

import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitDto;

public interface AdviceService {

    AdviceDto createAdvice(AdviceDto adviceDto);
    void processAdvices();
}
