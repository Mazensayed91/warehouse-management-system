package com.springboot.wms.restapi.restapi.Advice;

import com.springboot.wms.restapi.restapi.AdviceLine.AdviceLine;
import com.springboot.wms.restapi.restapi.AdviceLine.AdviceLineDto;
import com.springboot.wms.restapi.restapi.AdviceLine.AdviceLineRepository;
import com.springboot.wms.restapi.restapi.Employee.Employee;
import com.springboot.wms.restapi.restapi.Employee.EmployeeRepository;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnit;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitDto;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitRepository;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitService;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitType;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitTypeRepository;
import com.springboot.wms.restapi.restapi.SkuQuantityUnit.SkuQuantityUnit;
import com.springboot.wms.restapi.restapi.SkuQuantityUnit.SkuQuantityUnitRepository;
import com.springboot.wms.restapi.restapi.Supplier.Supplier;
import com.springboot.wms.restapi.restapi.Supplier.SupplierRepository;
import org.springframework.boot.context.config.ConfigDataResource;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class AdviceServiceImpl implements AdviceService {

    private AdviceRepository adviceRepository;
    private SupplierRepository supplierRepository;
    private EmployeeRepository employeeRepository;
    private SkuQuantityUnitRepository skuQuantityUnitRepository;
    private AdviceLineRepository adviceLineRepository;

    private Supplier supplier;
    private Employee employee;
    private AdviceLine adviceLine;

    public AdviceServiceImpl(AdviceRepository adviceRepository, SupplierRepository supplierRepository,
                             EmployeeRepository employeeRepository,
                             SkuQuantityUnitRepository skuQuantityUnitRepository,
                             AdviceLineRepository adviceLineRepository){
        this.adviceRepository = adviceRepository;
        this.supplierRepository = supplierRepository;
        this.employeeRepository = employeeRepository;
        this.skuQuantityUnitRepository = skuQuantityUnitRepository;
        this.adviceLineRepository = adviceLineRepository;
    }

    @Transactional
    @Override
    public AdviceDto createAdvice(AdviceDto adviceDto){
        // convert DTO to entity
        Advice advice = mapToEntity(adviceDto);

        // retrieve supplier and employee by id and save them
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

        // create advice lines and save them in the db
        saveAdviceLines(adviceDto.getAdvice_line_dtos(), advice);

        // create entity to DB
        Advice newAdvice = adviceRepository.save(advice);

        return mapToDto(newAdvice);
    }

    private void saveAdviceLines(Set <AdviceLineDto> adviceLineDtos, Advice advice){

        // loop over advice line dtos to save them in the advice line table
        adviceLineDtos.forEach(adviceLineDto -> {
            AdviceLine adviceLine = new AdviceLine();
            adviceLine.setAdvice(advice);
            adviceLine.setQuantity(adviceLineDto.getQuantity());
            adviceLine.setPrice_after(adviceLineDto.getUnit_price());
            adviceLine.setExpire_date(adviceLineDto.getExpire_date());
            System.out.println(adviceLineDto.getExpire_date().toString());
            // get the associated sku qu u with this advice line
            SkuQuantityUnit skuQuantityUnit = skuQuantityUnitRepository.getById(adviceLineDto.getSku_quantity_unit_id());

            adviceLine.setSku_quantity_unit(skuQuantityUnit);

            adviceLineRepository.save(adviceLine);
        });
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
