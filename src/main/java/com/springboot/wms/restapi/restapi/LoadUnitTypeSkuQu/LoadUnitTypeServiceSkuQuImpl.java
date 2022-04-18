package com.springboot.wms.restapi.restapi.LoadUnitTypeSkuQu;

import com.springboot.wms.restapi.restapi.Dimensions.Dimensions;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitType;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitTypeDto;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitTypeRepository;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitTypeService;
import com.springboot.wms.restapi.restapi.SkuQuantityUnit.SkuQuantityUnit;
import com.springboot.wms.restapi.restapi.SkuQuantityUnit.SkuQuantityUnitDto;
import com.springboot.wms.restapi.restapi.SkuQuantityUnit.SkuQuantityUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResource;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LoadUnitTypeServiceSkuQuImpl implements LoadUnitTypeSkuQuService {

    @Autowired
    private LoadUnitTypeSkuQuRepository loadUnitTypeSkuQuRepository;

    @Autowired
    private LoadUnitTypeRepository loadUnitTypeRepository;

    @Autowired
    private SkuQuantityUnitRepository skuQuantityUnitRepository;


    public LoadUnitTypeServiceSkuQuImpl(LoadUnitTypeSkuQuRepository loadUnitTypeSkuQuRepository,
                                        LoadUnitTypeRepository loadUnitTypeRepository,
                                        SkuQuantityUnitRepository skuQuantityUnitRepository){
        this.loadUnitTypeSkuQuRepository = loadUnitTypeSkuQuRepository;
        this.loadUnitTypeRepository = loadUnitTypeRepository;
        this.skuQuantityUnitRepository = skuQuantityUnitRepository;

    }

    @Transactional
    @Override
    public LoadUnitTypeSkuQuDto createLoadUnitTypeSkuQu(LoadUnitTypeSkuQuDto loadUnitTypeSkuQuDto){

        // convert DTO to entity
        LoadUnitTypeSkuQu loadUnitTypeSkuQu = mapToEntity(loadUnitTypeSkuQuDto);

        // create entity to DB
        LoadUnitTypeSkuQu newLoadUnitTypeSkuQu = loadUnitTypeSkuQuRepository.save(loadUnitTypeSkuQu);

        return mapToDto(newLoadUnitTypeSkuQu);
    }


    private LoadUnitTypeSkuQuDto mapToDto(LoadUnitTypeSkuQu loadUnitTypeSkuQu){

        LoadUnitTypeSkuQuDto loadUnitTypeSkuQuDto = new LoadUnitTypeSkuQuDto();

        loadUnitTypeSkuQuDto.setId(loadUnitTypeSkuQu.getId());
        loadUnitTypeSkuQuDto.setOrientation(loadUnitTypeSkuQu.getOrientation());
        loadUnitTypeSkuQuDto.setQuantity(loadUnitTypeSkuQuDto.getQuantity());
        loadUnitTypeSkuQuDto.setLoad_unit_type_id(loadUnitTypeSkuQuDto.getLoad_unit_type_id());
        loadUnitTypeSkuQuDto.setSku_quantity_unit_id(loadUnitTypeSkuQuDto.getSku_quantity_unit_id());

        return loadUnitTypeSkuQuDto;
    }

    private LoadUnitTypeSkuQu mapToEntity(LoadUnitTypeSkuQuDto loadUnitTypeSkuQuDto){

        LoadUnitTypeSkuQu loadUnitTypeSkuQu = new LoadUnitTypeSkuQu();


        LoadUnitType loadUnitType = loadUnitTypeRepository.findById(loadUnitTypeSkuQuDto.getLoad_unit_type_id()).orElseThrow(
                () -> new ConfigDataResourceNotFoundException(new ConfigDataResource() {
                }, new Throwable())
        );

        loadUnitTypeSkuQu.setLoadUnitType(loadUnitType);


        SkuQuantityUnit skuQuantityUnit = skuQuantityUnitRepository.findById(loadUnitTypeSkuQuDto.getSku_quantity_unit_id()).orElseThrow(
                () -> new ConfigDataResourceNotFoundException(new ConfigDataResource() {
                }, new Throwable())
        );

        loadUnitTypeSkuQu.setSkuQuantityUnit(skuQuantityUnit);


        loadUnitTypeSkuQu.setOrientation(loadUnitTypeSkuQuDto.getOrientation());
        loadUnitTypeSkuQu.setQuantity(loadUnitTypeSkuQuDto.getQuantity());

        return loadUnitTypeSkuQu;
    }
}
