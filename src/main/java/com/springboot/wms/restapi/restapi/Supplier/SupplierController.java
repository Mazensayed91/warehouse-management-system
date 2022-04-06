package com.springboot.wms.restapi.restapi.Supplier;

import com.springboot.wms.restapi.restapi.Employee.EmployeeDto;
import com.springboot.wms.restapi.restapi.Employee.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // create supplier
    @PostMapping
    public ResponseEntity<SupplierDto> createSupplier(@RequestBody SupplierDto supplierDtp){
        return new ResponseEntity<>(supplierService.createSupplier(supplierDtp), HttpStatus.CREATED);
    }

    // get suppliers
    @GetMapping
    public List<SupplierDto> getSuppliers(){
        return supplierService.getSuppliers();
    }
}
