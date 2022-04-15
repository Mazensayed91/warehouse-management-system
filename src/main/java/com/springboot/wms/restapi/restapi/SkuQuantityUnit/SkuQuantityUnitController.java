package com.springboot.wms.restapi.restapi.SkuQuantityUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class SkuQuantityUnitController {

    private SkuQuantityUnitService skuQuantityUnitService;

    public SkuQuantityUnitController(SkuQuantityUnitService skuQuantityUnitService) {
        this.skuQuantityUnitService = skuQuantityUnitService;
    }

    // create skuQuantityUnit
    @PostMapping("/sku_quantity_unit")
    public ResponseEntity<SkuQuantityUnitDto> createSkuQuantityUnit(@RequestBody SkuQuantityUnitDto skuQuantityUnitDto){
        return new ResponseEntity<>(skuQuantityUnitService.createSkuQuantityUnit(skuQuantityUnitDto), HttpStatus.CREATED);
    }

}
