package com.springboot.wms.restapi.restapi.LoadUnit;

import java.util.List;

public interface LoadUnitService {
    LoadUnitDto createLoadUnit(long loadUnitTypeId, LoadUnitDto loadUnitDto);
    //List<LoadUnitDto> getLoadUnits();
}
