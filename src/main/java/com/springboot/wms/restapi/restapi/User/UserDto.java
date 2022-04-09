package com.springboot.wms.restapi.restapi.User;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {

    private long id;
    private String first_name;
    private String last_name;
    private String address;
    private String number;
    private String password;
    private String email;
    Set<Long> roles_ids;
}
