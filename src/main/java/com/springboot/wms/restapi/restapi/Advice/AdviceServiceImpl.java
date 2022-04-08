package com.springboot.wms.restapi.restapi.Advice;

import com.springboot.wms.restapi.restapi.Employee.Employee;
import com.springboot.wms.restapi.restapi.Employee.EmployeeRepository;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnit;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitDto;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitRepository;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitService;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitType;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitTypeRepository;
import com.springboot.wms.restapi.restapi.Supplier.Supplier;
import com.springboot.wms.restapi.restapi.Supplier.SupplierRepository;
import org.springframework.boot.context.config.ConfigDataResource;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdviceServiceImpl implements AdviceService {

    private AdviceRepository adviceRepository;
    private SupplierRepository supplierRepository;
    private EmployeeRepository employeeRepository;
    private Supplier supplier;
    private Employee employee;

    public AdviceServiceImpl(AdviceRepository adviceRepository, SupplierRepository supplierRepository,
                             EmployeeRepository employeeRepository){
        this.adviceRepository = adviceRepository;
        this.supplierRepository = supplierRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    @Override
    public AdviceDto createAdvice(AdviceDto adviceDto){
        // convert DTO to entity
        Advice advice = mapToEntity(adviceDto);

        // retrieve load unit type by id
        supplier = supplierRepository.findById(adviceDto.getSupplier_id()).orElseThrow(
                () -> new ConfigDataResourceNotFoundException(new ConfigDataResource() {
                }, new Throwable())
        );
        employee = employeeRepository.findById(adviceDto.getEmployee_id()).orElseThrow(
                () -> new ConfigDataResourceNotFoundException(new ConfigDataResource() {
                }, new Throwable())
        );
        advice.setSupplier(supplier);
        advice.setEmployee(employee);
        advice.setStatus(Advice.Status.IN_PROGRESS);

        // create entity to DB
        Advice newAdvice = adviceRepository.save(advice);

        return mapToDto(newAdvice);
    }


    private AdviceDto mapToDto(Advice advice){
        AdviceDto adviceDto = new AdviceDto();

        adviceDto.setId(advice.getId());
        adviceDto.setDiscount(advice.getDiscount());
        adviceDto.setSub_total(advice.getSub_total());
        adviceDto.setQuantity(advice.getQuantity());
        adviceDto.setSupplier_id(advice.getSupplier().getId());
        adviceDto.setEmployee_id(advice.getEmployee().getId());

        return adviceDto;
    }

    private Advice mapToEntity(AdviceDto adviceDto){

        Advice advice = new Advice();

        advice.setDiscount(adviceDto.getDiscount());
        advice.setQuantity(adviceDto.getQuantity());
        advice.setSub_total(adviceDto.getSub_total());

        return advice;
    }
}
