package com.springboot.wms.restapi.restapi.Sku;

import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitDto;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/")
public class SkuController {

    private SkuService skuService;

    public SkuController(SkuService skuService) {
        this.skuService = skuService;
    }

    // create loadUnit
    @PostMapping("/sku")
    public ResponseEntity<SkuDto> createSku(@RequestBody SkuDto skuDto) throws SQLException {
        return new ResponseEntity<>(skuService.createSku(skuDto), HttpStatus.CREATED);
    }

}
