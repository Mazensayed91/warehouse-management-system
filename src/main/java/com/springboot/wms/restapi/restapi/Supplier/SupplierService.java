package com.springboot.wms.restapi.restapi.Supplier;

import com.springboot.wms.restapi.restapi.Employee.EmployeeDto;

import java.util.List;

public interface SupplierService {
    SupplierDto createSupplier(SupplierDto supplierDto);
    List<SupplierDto> getSuppliers();
}
