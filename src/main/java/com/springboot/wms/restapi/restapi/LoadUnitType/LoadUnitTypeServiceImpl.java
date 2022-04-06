package com.springboot.wms.restapi.restapi.LoadUnitType;

import com.springboot.wms.restapi.restapi.ContactPerson.ContactPerson;
import com.springboot.wms.restapi.restapi.Dimensions.Dimensions;
import com.springboot.wms.restapi.restapi.Employee.Employee;
import com.springboot.wms.restapi.restapi.Employee.EmployeeDto;
import com.springboot.wms.restapi.restapi.Employee.EmployeeRepository;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnit;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitDto;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitRepository;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitService;
import com.springboot.wms.restapi.restapi.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoadUnitTypeServiceImpl implements LoadUnitTypeService {

    private LoadUnitTypeRepository loadUnitTypeRepository;

    public LoadUnitTypeServiceImpl(LoadUnitTypeRepository loadUnitTypeRepository){
        this.loadUnitTypeRepository = loadUnitTypeRepository;
    }

    @Transactional
    @Override
    public LoadUnitTypeDto createLoadUnitType(LoadUnitTypeDto loadUnitTypeDto){

        // convert DTO to entity
        LoadUnitType loadUnitType = mapToEntity(loadUnitTypeDto);

        // create entity to DB
        LoadUnitType newLoadUnitType = loadUnitTypeRepository.save(loadUnitType);

        return mapToDto(newLoadUnitType);
    }


    private LoadUnitTypeDto mapToDto(LoadUnitType loadUnitType){

        LoadUnitTypeDto loadUnitTypeDto = new LoadUnitTypeDto();

        loadUnitTypeDto.setId(loadUnitType.getId());
        loadUnitTypeDto.setName(loadUnitType.getName());
        loadUnitTypeDto.setHeight(loadUnitType.getDimensions().getHeight());
        loadUnitTypeDto.setWidth(loadUnitType.getDimensions().getWidth());
        loadUnitTypeDto.setLength(loadUnitType.getDimensions().getLength());

        return loadUnitTypeDto;
    }

    private LoadUnitType mapToEntity(LoadUnitTypeDto loadUnitTypeDto){

        LoadUnitType loadUnitType = new LoadUnitType();
        Dimensions dimensions = new Dimensions();
        dimensions.setHeight(loadUnitTypeDto.getHeight());
        dimensions.setLength(loadUnitTypeDto.getLength());
        dimensions.setWidth(loadUnitTypeDto.getWidth());

        loadUnitType.setDimensions(dimensions);
        loadUnitType.setName(loadUnitTypeDto.getName());

        return loadUnitType;
    }
}
