package com.springboot.wms.restapi.restapi.LoadUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class LoadUnitController {

    private LoadUnitService loadUnitService;

    public LoadUnitController(LoadUnitService loadUnitService) {
        this.loadUnitService = loadUnitService;
    }

    // create loadUnit
    @PostMapping("/load_units_types/{loadUnitTypeId}/add")
    public ResponseEntity<LoadUnitDto> createLoadUnit(@PathVariable(value = "loadUnitTypeId") long loadUnitTypeId,
                                                    @RequestBody LoadUnitDto loadUnitDto) throws SQLException {
        return new ResponseEntity<>(loadUnitService.createLoadUnit(loadUnitTypeId,loadUnitDto), HttpStatus.CREATED);
    }

}
