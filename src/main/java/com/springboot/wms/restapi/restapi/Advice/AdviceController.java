package com.springboot.wms.restapi.restapi.Advice;

import com.springboot.wms.restapi.restapi.Customer.CustomerDto;
import com.springboot.wms.restapi.restapi.Customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advices")
public class AdviceController {

    private AdviceService adviceService;

    public AdviceController(AdviceService adviceService) {
        this.adviceService = adviceService;
    }

    // create advice
    @PostMapping
    public ResponseEntity<AdviceDto> createAdvice(@RequestBody AdviceDto adviceDto){
        return new ResponseEntity<>(adviceService.createAdvice(adviceDto), HttpStatus.CREATED);
    }
}
