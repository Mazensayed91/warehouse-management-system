package com.springboot.wms.restapi.restapi.Role;

import lombok.Data;

import java.util.Set;

@Data
public class RoleDto {

    private long id;
    private String title;
    private String description;
    Set<Long> permissions_ids;
}
