package com.springboot.wms.restapi.restapi.LoadUnitType;

import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitDto;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class LoadUnitTypeController {

    private LoadUnitTypeService loadUnitTypeService;

    public LoadUnitTypeController(LoadUnitTypeService loadUnitTypeService) {
        this.loadUnitTypeService = loadUnitTypeService;
    }

    // create loadUnitType
    @PostMapping("/load_units_types")
    public ResponseEntity<LoadUnitTypeDto> createLoadUnitType(@RequestBody LoadUnitTypeDto loadUnitTypeDto){
        return new ResponseEntity<>(loadUnitTypeService.createLoadUnitType(loadUnitTypeDto), HttpStatus.CREATED);
    }

}
