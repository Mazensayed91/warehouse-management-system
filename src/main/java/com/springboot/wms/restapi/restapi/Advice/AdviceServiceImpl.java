package com.springboot.wms.restapi.restapi.Advice;

import com.springboot.wms.restapi.restapi.AdviceLineLoadUnit.AdviceLineLoadUnit;
import com.springboot.wms.restapi.restapi.AdviceLineLoadUnit.AdviceLineLoadUnitRepository;
import com.springboot.wms.restapi.restapi.Inventory.Inventory;
import com.springboot.wms.restapi.restapi.Inventory.InventoryRepository;
import com.springboot.wms.restapi.restapi.InventoryLoadUnit.InventoryLoadUnit;
import com.springboot.wms.restapi.restapi.InventoryLoadUnit.InventoryLoadUnitRepository;
import com.springboot.wms.restapi.restapi.LoadUnitTypeSkuQu.LoadUnitTypeSkuQu;
import com.springboot.wms.restapi.restapi.LoadUnitTypeSkuQu.LoadUnitTypeSkuQuRepository;
import lombok.extern.slf4j.Slf4j;

import com.springboot.wms.restapi.restapi.AdviceLine.AdviceLine;
import com.springboot.wms.restapi.restapi.AdviceLine.AdviceLineDto;
import com.springboot.wms.restapi.restapi.AdviceLine.AdviceLineRepository;
import com.springboot.wms.restapi.restapi.Employee.Employee;
import com.springboot.wms.restapi.restapi.Employee.EmployeeRepository;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitRepository;
import com.springboot.wms.restapi.restapi.SkuQuantityUnit.SkuQuantityUnit;
import com.springboot.wms.restapi.restapi.SkuQuantityUnit.SkuQuantityUnitRepository;
import com.springboot.wms.restapi.restapi.Supplier.Supplier;
import com.springboot.wms.restapi.restapi.Supplier.SupplierRepository;
import org.springframework.boot.context.config.ConfigDataResource;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@EnableScheduling
public class AdviceServiceImpl implements AdviceService {

    private AdviceRepository adviceRepository;
    private SupplierRepository supplierRepository;
    private EmployeeRepository employeeRepository;
    private SkuQuantityUnitRepository skuQuantityUnitRepository;
    private AdviceLineRepository adviceLineRepository;
    private LoadUnitRepository loadUnitRepository;
    private LoadUnitTypeSkuQuRepository loadUnitTypeSkuQuRepository;
    private AdviceLineLoadUnitRepository adviceLineLoadUnitRepository;
    private InventoryRepository inventoryRepository;
    private InventoryLoadUnitRepository inventoryLoadUnitRepository;

    private Supplier supplier;
    private Employee employee;
    private AdviceLine adviceLine;

    public AdviceServiceImpl(AdviceRepository adviceRepository, SupplierRepository supplierRepository,
                             EmployeeRepository employeeRepository,
                             SkuQuantityUnitRepository skuQuantityUnitRepository,
                             AdviceLineRepository adviceLineRepository,
                             LoadUnitRepository loadUnitRepository,
                             LoadUnitTypeSkuQuRepository loadUnitTypeSkuQuRepository,
                             AdviceLineLoadUnitRepository adviceLineLoadUnitRepository,
                             InventoryRepository inventoryRepository,
                             InventoryLoadUnitRepository inventoryLoadUnitRepository){
        this.adviceRepository = adviceRepository;
        this.supplierRepository = supplierRepository;
        this.employeeRepository = employeeRepository;
        this.skuQuantityUnitRepository = skuQuantityUnitRepository;
        this.adviceLineRepository = adviceLineRepository;
        this.loadUnitRepository = loadUnitRepository;
        this.loadUnitTypeSkuQuRepository = loadUnitTypeSkuQuRepository;
        this.adviceLineLoadUnitRepository = adviceLineLoadUnitRepository;
        this.inventoryRepository = inventoryRepository;
        this.inventoryLoadUnitRepository = inventoryLoadUnitRepository;
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

    // load the supplied items to suitable load units
    public void processAdvices(){

        //Set<Advice> inProgressAdvices = new HashSet<>();

        adviceRepository.findByStatus(Advice.Status.IN_PROGRESS).forEach(adviceID -> {
            System.out.println("advice id"+ adviceID);
            Advice advice = adviceRepository.findById(adviceID).orElseThrow(
                    () -> new ConfigDataResourceNotFoundException(new ConfigDataResource() {
                    }, new Throwable()));
            advice.setStatus(Advice.Status.RECEIVED);
            adviceRepository.save(advice);
            if(!Objects.isNull(advice.getAdviceLines())) {
                advice.getAdviceLines().forEach(adviceLine -> {
                    AtomicInteger adviceLineQuantity = new AtomicInteger(adviceLine.getQuantity());
                    System.out.println("hhhhhhhhh" + adviceLineQuantity.get());
                    loadUnitRepository.findAll().forEach(loadUnit -> {
                        LoadUnitTypeSkuQu loadUnitTypeSkuQu = loadUnitTypeSkuQuRepository.findBySkuQuantityUnitIdAndLoadUnitTypeId(
                                adviceLine.getSku_quantity_unit().getId(), loadUnit.getLoad_unit_type().getId());
                        System.out.println("hh" + adviceLineQuantity.get());
                        System.out.println(loadUnitTypeSkuQu.getQuantity());

                        // check if this load unit type can hold 1 or more sku qu unit
                        if (loadUnitTypeSkuQu.getQuantity() > 0 && adviceLineQuantity.get() > 0) {
                            AdviceLineLoadUnit adviceLineLoadUnit = new AdviceLineLoadUnit();
                            adviceLineLoadUnit.setAdviceLine(adviceLine);
                            adviceLineLoadUnit.setLoadUnit(loadUnit);
                            System.out.println(loadUnitTypeSkuQu.getQuantity());

                            if (loadUnitTypeSkuQu.getQuantity() > adviceLineQuantity.get()) {
                                adviceLineLoadUnit.setAvailable_quantity(adviceLineQuantity.get());
                            } else {
                                adviceLineLoadUnit.setAvailable_quantity(loadUnitTypeSkuQu.getQuantity());
                                System.out.println(loadUnitTypeSkuQu.getQuantity());
                            }
                            System.out.println(3);

                            adviceLineLoadUnitRepository.save(adviceLineLoadUnit);
                            System.out.println(4);

                            adviceLineQuantity.addAndGet(-loadUnitTypeSkuQu.getQuantity());
                        }

                        // fill inventory
                        // first check if inventory item is there, if it is then increment count_global
                        Inventory inventory = inventoryRepository.findByExpireDateAndSupplierAndSkuQuantityUnit(
                                adviceLine.getExpire_date(),
                                advice.getSupplier(),
                                adviceLine.getSku_quantity_unit());
                        if(!Objects.isNull(inventory)){
                            inventory.setCount_global(inventory.getCount_global()+adviceLine.getQuantity());
                        }
                        // if not there, create one
                        else{

                            inventory = new Inventory();
                            inventory.setExpireDate(adviceLine.getExpire_date());
                            inventory.setSupplier(advice.getSupplier());
                            inventory.setSkuQuantityUnit(adviceLine.getSku_quantity_unit());
                            inventory.setCount_global(adviceLine.getQuantity());

                            inventoryRepository.save(inventory);
                        }
                        // create inventory load unit
                        InventoryLoadUnit inventoryLoadUnit = new InventoryLoadUnit();
                        inventoryLoadUnit.setInventory(inventory);
                        inventoryLoadUnit.setLoad_unit(loadUnit);
                    });
                });
            }
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
