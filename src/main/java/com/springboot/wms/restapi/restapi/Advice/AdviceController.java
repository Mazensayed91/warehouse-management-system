package com.springboot.wms.restapi.restapi.Advice;

import com.springboot.wms.restapi.restapi.Customer.CustomerDto;
import com.springboot.wms.restapi.restapi.Customer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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

    // process advices (scheduling)
    @Scheduled(initialDelay = 10000, fixedDelay = 10000)
    @PostMapping("/process")
    public void processAdvices() {
        log.info("Started processing advices");
        adviceService.processAdvices();
        log.info("Done with processing advices");
    }
}
