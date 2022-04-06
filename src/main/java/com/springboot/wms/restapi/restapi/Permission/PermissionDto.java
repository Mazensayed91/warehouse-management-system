package com.springboot.wms.restapi.restapi.Permission;

import lombok.Data;

@Data
public class PermissionDto {

    private long id;
    private String title;
    private String description;
    private boolean active;
}
