package com.springboot.wms.restapi.restapi.LoadUnit;

import com.springboot.wms.restapi.restapi.Dimensions.Dimensions;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitType;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitTypeDto;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitTypeRepository;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoadUnitServiceImpl implements LoadUnitService {

    private LoadUnitRepository loadUnitRepository;
    private LoadUnitTypeRepository loadUnitTypeRepository;

    public LoadUnitServiceImpl(LoadUnitRepository loadUnitRepository, LoadUnitTypeRepository loadUnitTypeRepository){
        this.loadUnitRepository = loadUnitRepository;
        this.loadUnitTypeRepository = loadUnitTypeRepository;
    }

    @Transactional
    @Override
    public LoadUnitDto createLoadUnit(long loadUnitTypeId, LoadUnitDto loadUnitDto){
        // convert DTO to entity
        LoadUnit loadUnit = mapToEntity(loadUnitDto);

        // retrieve load unit type by id
        LoadUnitType loadUnitType = loadUnitTypeRepository.findById(loadUnitTypeId).orElseThrow(
                () -> new ConfigDataResourceNotFoundException(new ConfigDataResource() {
                }, new Throwable())
        );
        loadUnit.setLoad_unit_type(loadUnitType);

        // create entity to DB
        LoadUnit newLoadUnit = loadUnitRepository.save(loadUnit);

        return mapToDto(newLoadUnit);
    }


    private LoadUnitDto mapToDto(LoadUnit loadUnit){
        LoadUnitDto loadUnitDto = new LoadUnitDto();

        loadUnitDto.setId(loadUnit.getId());

        return loadUnitDto;
    }

    private LoadUnit mapToEntity(LoadUnitDto loadUnitDto){

        LoadUnit loadUnit = new LoadUnit();
        return loadUnit;
    }
}
