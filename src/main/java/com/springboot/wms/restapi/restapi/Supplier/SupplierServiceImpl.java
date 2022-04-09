package com.springboot.wms.restapi.restapi.Supplier;

import com.springboot.wms.restapi.restapi.ContactPerson.ContactPerson;
import com.springboot.wms.restapi.restapi.Employee.Employee;
import com.springboot.wms.restapi.restapi.Employee.EmployeeDto;
import com.springboot.wms.restapi.restapi.Employee.EmployeeRepository;
import com.springboot.wms.restapi.restapi.Employee.EmployeeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    @Override
    public SupplierDto createSupplier(SupplierDto supplierDto){
        // convert DTO to entity
        Supplier supplier = mapToEntity(supplierDto);

        Supplier newSupplier = supplierRepository.save(supplier);

        // convert saved entity to DTO
        SupplierDto supplierResponse;

        supplierResponse = mapToDto(newSupplier);

        return supplierResponse;
    }

    @Override
    public List<SupplierDto> getSuppliers(){
        return supplierRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private SupplierDto mapToDto(Supplier supplier){
        SupplierDto supplierDto = new SupplierDto();

        supplierDto.setId(supplier.getId());

        return supplierDto;
    }

    private Supplier mapToEntity(SupplierDto supplierDto){
        return new Supplier();
    }
}
