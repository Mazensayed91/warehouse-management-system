package com.springboot.wms.restapi.restapi.Role;

import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitDto;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Set;

@RestController
@RequestMapping("/api/")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // create loadUnit
    @RequestMapping(value = "/role", method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) throws SQLException {
        return new ResponseEntity<>(roleService.createRole(roleDto), HttpStatus.CREATED);
    }

}
