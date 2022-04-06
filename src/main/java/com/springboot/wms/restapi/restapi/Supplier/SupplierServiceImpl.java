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
        supplierDto.setFirst_name(supplier.getContactPerson().getFirst_name());
        supplierDto.setLast_name(supplier.getContactPerson().getLast_name());
        supplierDto.setNumber(supplier.getContactPerson().getNumber());
        supplierDto.setAddress(supplier.getContactPerson().getAddress());

        return supplierDto;
    }

    private Supplier mapToEntity(SupplierDto supplierDto){

        Supplier supplier = new Supplier();
        ContactPerson contactPerson = new ContactPerson() ;
        contactPerson.setFirst_name(supplierDto.getFirst_name());
        contactPerson.setLast_name(supplierDto.getLast_name());
        contactPerson.setAddress(supplierDto.getAddress());
        contactPerson.setNumber(supplierDto.getNumber());

        supplier.setContactPerson(contactPerson);

        return supplier;
    }
}
