package com.springboot.wms.restapi.restapi.Permission;

import com.springboot.wms.restapi.restapi.Customer.CustomerDto;

import java.util.List;

public interface PermissionService {
    PermissionDto createPermission(PermissionDto permissionDto);
    List<PermissionDto> getPermissions();
}
