package com.springboot.wms.restapi.restapi.SkuQuantityUnit;

import com.springboot.wms.restapi.restapi.Dimensions.Dimensions;
import com.springboot.wms.restapi.restapi.Employee.EmployeeController;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnit;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitDto;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitRepository;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitService;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitType;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitTypeRepository;
import com.springboot.wms.restapi.restapi.LoadUnitTypeSkuQu.LoadUnitTypeSkuQuController;
import com.springboot.wms.restapi.restapi.LoadUnitTypeSkuQu.LoadUnitTypeSkuQuDto;
import com.springboot.wms.restapi.restapi.LoadUnitTypeSkuQu.LoadUnitTypeSkuQuService;
import com.springboot.wms.restapi.restapi.Sku.Sku;
import com.springboot.wms.restapi.restapi.Sku.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResource;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class SkuQuantityUnitServiceImpl implements SkuQuantityUnitService {

    @Autowired
    private SkuQuantityUnitRepository skuQuantityUnitRepository;
    @Autowired
    private SkuRepository skuRepository;
    @Autowired
    private LoadUnitTypeRepository loadUnitTypeRepository;

    @Autowired
    private LoadUnitTypeSkuQuController loadUnitTypeSkuQuController;

    public SkuQuantityUnitServiceImpl(SkuQuantityUnitRepository skuQuantityUnitRepository,
                                      SkuRepository skuRepository,
                                      LoadUnitTypeRepository loadUnitTypeRepository,
                                      LoadUnitTypeSkuQuController loadUnitTypeSkuQuController){
        this.skuQuantityUnitRepository = skuQuantityUnitRepository;
        this.skuRepository = skuRepository;
        this.loadUnitTypeRepository = loadUnitTypeRepository;
        this.loadUnitTypeSkuQuController = loadUnitTypeSkuQuController;
    }

    @Transactional
    @Override
    public SkuQuantityUnitDto createSkuQuantityUnit(SkuQuantityUnitDto skuQuantityUnitDto){
        // convert DTO to entity
        SkuQuantityUnit skuQuantityUnit = mapToEntity(skuQuantityUnitDto);

        // retrieve the sku by id and set this sku to the sku_q_u

        Sku sku = skuRepository.findById(skuQuantityUnitDto.getSku_id()).orElseThrow(
                () -> new ConfigDataResourceNotFoundException(new ConfigDataResource() {
                }, new Throwable())
        );
        skuQuantityUnit.setSku(sku);

        // create entity to DB
        SkuQuantityUnit newSkuQuantityUnit = skuQuantityUnitRepository.save(skuQuantityUnit);

        // determine the best position for the sku_q_u in each load unit type
        ArrayList<LoadUnitType> loadUnitTypes  = new ArrayList<>(loadUnitTypeRepository.findAll());
        loadUnitTypes.forEach(loadUnitType -> {
            HashMap.Entry<String, Integer> bestPosition= bestPosition(skuQuantityUnit, loadUnitType);
            System.out.println("Here el max " + bestPosition.toString());
            LoadUnitTypeSkuQuDto loadUnitTypeSkuQuDto = new LoadUnitTypeSkuQuDto();
            loadUnitTypeSkuQuDto.setSku_quantity_unit_id(skuQuantityUnit.getId());
            loadUnitTypeSkuQuDto.setLoad_unit_type_id(loadUnitType.getId());
            loadUnitTypeSkuQuDto.setQuantity(bestPosition.getValue());
            loadUnitTypeSkuQuDto.setOrientation(bestPosition.getKey());
            System.out.println("Here el maxx " + loadUnitTypeSkuQuDto.toString());

            loadUnitTypeSkuQuController.createLoadUnitTypeSkuQu(loadUnitTypeSkuQuDto);
        });


        return mapToDto(newSkuQuantityUnit);
    }

    private Map.Entry<String, Integer> bestPosition(SkuQuantityUnit skuQuantityUnit, LoadUnitType loadUnitType){

        Dimensions dimensions = loadUnitType.getDimensions();
        double loadUnitHeight = dimensions.getHeight();
        double loadUnitWidth = dimensions.getWidth();
        double loadUnitLength = dimensions.getLength();

        // get number of items for each position
        HashMap<String, Integer> itemsPerPosition = new HashMap<>();

        itemsPerPosition.put("LWH", numberOfItemsFitsInLoadUnit(skuQuantityUnit.getDimensions().getLength(), loadUnitLength,
                skuQuantityUnit.getDimensions().getWidth(), loadUnitWidth,
                skuQuantityUnit.getDimensions().getHeight(), loadUnitHeight));

        itemsPerPosition.put("WLH", numberOfItemsFitsInLoadUnit(skuQuantityUnit.getDimensions().getWidth(), loadUnitWidth,
                skuQuantityUnit.getDimensions().getLength(), loadUnitLength,
                skuQuantityUnit.getDimensions().getHeight(), loadUnitHeight));

        itemsPerPosition.put("HLW", numberOfItemsFitsInLoadUnit(skuQuantityUnit.getDimensions().getHeight(), loadUnitHeight,
                skuQuantityUnit.getDimensions().getLength(), loadUnitLength,
                skuQuantityUnit.getDimensions().getWidth(), loadUnitWidth
        ));

        itemsPerPosition.put("HWL", numberOfItemsFitsInLoadUnit(skuQuantityUnit.getDimensions().getHeight(), loadUnitHeight,
                skuQuantityUnit.getDimensions().getWidth(), loadUnitWidth,
                skuQuantityUnit.getDimensions().getLength(), loadUnitLength
        ));

        itemsPerPosition.put("LHW", numberOfItemsFitsInLoadUnit(skuQuantityUnit.getDimensions().getLength(), loadUnitLength,
                skuQuantityUnit.getDimensions().getHeight(), loadUnitHeight,
                skuQuantityUnit.getDimensions().getWidth(), loadUnitWidth
        ));

        itemsPerPosition.put("WHL", numberOfItemsFitsInLoadUnit(skuQuantityUnit.getDimensions().getWidth(), loadUnitWidth,
                skuQuantityUnit.getDimensions().getHeight(), loadUnitHeight,
                skuQuantityUnit.getDimensions().getLength(), loadUnitLength
        ));

        return itemsPerPosition.entrySet().stream().max((loadUnit1, loadUnit2) -> loadUnit1.getValue() > loadUnit2.getValue() ? 1 : -1).get();

    }

    private int numberOfItemsFitsInLoadUnit(double length, double loadUnitLength,
                                               double width, double loadUnitWidth,
                                               double height, double loadUnitHeight){

        double noOfItemsInLength = loadUnitLength / length;
        double noOfItemsInWidth = loadUnitWidth / width;
        double noOfItemsInHeight = loadUnitHeight / height;

        return  (int)(noOfItemsInLength * noOfItemsInWidth * noOfItemsInHeight);

    }

    private SkuQuantityUnitDto mapToDto(SkuQuantityUnit skuQuantityUnit){

        SkuQuantityUnitDto skuQuantityUnitDto = new SkuQuantityUnitDto();

        skuQuantityUnitDto.setId(skuQuantityUnit.getId());
        skuQuantityUnitDto.setSingular_quantity(skuQuantityUnit.getSingular_quantity());
        skuQuantityUnitDto.setHeight(skuQuantityUnit.getDimensions().getHeight());
        skuQuantityUnitDto.setLength(skuQuantityUnit.getDimensions().getLength());
        skuQuantityUnitDto.setWidth(skuQuantityUnit.getDimensions().getWidth());
        skuQuantityUnitDto.setName(skuQuantityUnit.getName());

        return skuQuantityUnitDto;
    }

    private SkuQuantityUnit mapToEntity(SkuQuantityUnitDto skuQuantityUnitDto){

        SkuQuantityUnit skuQuantityUnit = new SkuQuantityUnit();

        Dimensions dimensions = new Dimensions();
        dimensions.setHeight(skuQuantityUnitDto.getHeight());
        dimensions.setWidth(skuQuantityUnitDto.getWidth());
        dimensions.setLength(skuQuantityUnitDto.getLength());

        skuQuantityUnit.setDimensions(dimensions);
        skuQuantityUnit.setName(skuQuantityUnitDto.getName());
        skuQuantityUnit.setSingular_quantity(skuQuantityUnitDto.getSingular_quantity());

        return skuQuantityUnit;
    }
}
