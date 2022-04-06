package com.springboot.wms.restapi.restapi.Permission;

import com.springboot.wms.restapi.restapi.Supplier.SupplierDto;
import com.springboot.wms.restapi.restapi.Supplier.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    // create permission
    @PostMapping
    public ResponseEntity<PermissionDto> createPermission(@RequestBody PermissionDto permissionDto){
        return new ResponseEntity<>(permissionService.createPermission(permissionDto), HttpStatus.CREATED);
    }

    // get permissions
    @GetMapping
    public List<PermissionDto> getPermissions(){
        return permissionService.getPermissions();
    }
}
