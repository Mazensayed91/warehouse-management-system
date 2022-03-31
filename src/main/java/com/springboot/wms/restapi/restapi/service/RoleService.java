package com.springboot.wms.restapi.restapi.service;

import com.springboot.wms.restapi.restapi.Role.RoleDto;

public interface RoleService {
    RoleDto createRole(long employeeId, RoleDto roleDto);
}
