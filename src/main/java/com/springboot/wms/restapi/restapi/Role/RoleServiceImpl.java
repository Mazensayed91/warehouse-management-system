package com.springboot.wms.restapi.restapi.Role;

import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnit;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitDto;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitRepository;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitService;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitType;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitTypeRepository;
import com.springboot.wms.restapi.restapi.Permission.Permission;
import com.springboot.wms.restapi.restapi.Permission.PermissionRepository;
import org.springframework.boot.context.config.ConfigDataResource;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private PermissionRepository permissionRepository;

    public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository){
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    @Override
    public RoleDto createRole( RoleDto roleDto){
        // convert DTO to entity
        Role role = mapToEntity(roleDto);

        // retrieve load unit type by id
        Set<Permission> permissions  = permissionRepository.findByPermissionIds(roleDto.getPermissions_ids());

        role.setPermissions(permissions);

        // create entity to DB
        Role newRole = roleRepository.save(role);

        return mapToDto(newRole);
    }


    private RoleDto mapToDto(Role role){

        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setTitle(role.getTitle());
        roleDto.setDescription(role.getDescription());
        roleDto.setPermissions_ids(role.getPermissions().stream().map(Permission::getId).collect(Collectors.toSet()));

        return roleDto;
    }

    private Role mapToEntity(RoleDto roleDto){

        Role role = new Role();
        role.setDescription(roleDto.getDescription());
        role.setTitle(roleDto.getTitle());
        role.setPermissions(permissionRepository.findByPermissionIds(roleDto.getPermissions_ids()));

        return role;
    }
}
