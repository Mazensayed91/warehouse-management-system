package com.springboot.wms.restapi.restapi.service.impl;

import com.springboot.wms.restapi.restapi.dto.RoleDto;
import com.springboot.wms.restapi.restapi.repository.RoleRepository;
import com.springboot.wms.restapi.restapi.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto createRole(long employeeId, RoleDto roleDto){
        return null;
    }
}
