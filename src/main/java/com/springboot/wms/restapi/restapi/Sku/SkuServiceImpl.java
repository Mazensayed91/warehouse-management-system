package com.springboot.wms.restapi.restapi.Sku;

import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnit;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitDto;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitRepository;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitService;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitType;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitTypeRepository;
import org.springframework.boot.context.config.ConfigDataResource;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SkuServiceImpl implements SkuService {

    private SkuRepository skuRepository;

    public SkuServiceImpl(SkuRepository skuRepository){
        this.skuRepository = skuRepository;
    }

    @Transactional
    @Override
    public SkuDto createSku(SkuDto skuDto){
        // convert DTO to entity
        Sku sku = mapToEntity(skuDto);

        // create entity to DB
        Sku newSku = skuRepository.save(sku);

        return mapToDto(newSku);
    }


    private SkuDto mapToDto(Sku sku){
        SkuDto skuDto = new SkuDto();

        skuDto.setId(sku.getId());
        skuDto.setDescription(sku.getDescription());
        skuDto.setName(sku.getName());
        skuDto.set_expired(sku.is_expired());

        return skuDto;
    }

    private Sku mapToEntity(SkuDto skuDto){

        Sku sku = new Sku();
        sku.setName(skuDto.getName());
        sku.setDescription(skuDto.getDescription());
        sku.set_expired(skuDto.is_expired());

        return sku;
    }
}
