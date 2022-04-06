package com.springboot.wms.restapi.restapi.Permission;

import com.springboot.wms.restapi.restapi.ContactPerson.ContactPerson;
import com.springboot.wms.restapi.restapi.Customer.Customer;
import com.springboot.wms.restapi.restapi.Customer.CustomerDto;
import com.springboot.wms.restapi.restapi.Customer.CustomerRepository;
import com.springboot.wms.restapi.restapi.Customer.CustomerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    private PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository){
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    @Override
    public PermissionDto createPermission(PermissionDto permissionDto){
        // convert DTO to entity
        Permission permission = mapToEntity(permissionDto);

        Permission newPermission = permissionRepository.save(permission);

        // convert saved entity to DTO
        PermissionDto permissionResponse;

        permissionResponse = mapToDto(newPermission);

        return permissionResponse;
    }

    @Override
    public List<PermissionDto> getPermissions(){
        return permissionRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private PermissionDto mapToDto(Permission permission){
        PermissionDto permissionDto = new PermissionDto();

        permissionDto.setId(permission.getId());
        permissionDto.setActive(permission.isActive());
        permissionDto.setDescription(permission.getDescription());
        permissionDto.setTitle(permission.getTitle());

        return permissionDto;
    }

    private Permission mapToEntity(PermissionDto permissionDto){

        Permission permission = new Permission();

        permission.setDescription(permissionDto.getDescription());
        permission.setTitle(permissionDto.getTitle());
        permission.setActive(permissionDto.isActive());

        return permission;
    }
}
