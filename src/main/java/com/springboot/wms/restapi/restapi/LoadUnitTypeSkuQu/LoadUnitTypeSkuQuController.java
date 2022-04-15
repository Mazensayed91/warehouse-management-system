package com.springboot.wms.restapi.restapi.LoadUnitTypeSkuQu;

import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitTypeDto;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class LoadUnitTypeSkuQuController {

    private LoadUnitTypeSkuQuService loadUnitTypeSkuQuService;

    public LoadUnitTypeSkuQuController(LoadUnitTypeSkuQuService loadUnitTypeSkuQuService) {
        this.loadUnitTypeSkuQuService = loadUnitTypeSkuQuService;
    }

    // create loadUnitTypeSkuQu
    @PostMapping("/load_units_type_sku_qu")
    public ResponseEntity<LoadUnitTypeSkuQuDto> createLoadUnitTypeSkuQu(@RequestBody LoadUnitTypeSkuQuDto loadUnitTypeSkuQuDto){
        return new ResponseEntity<>(loadUnitTypeSkuQuService.createLoadUnitTypeSkuQu(loadUnitTypeSkuQuDto), HttpStatus.CREATED);
    }

}
