package com.springboot.wms.restapi.restapi.User;

import com.springboot.wms.restapi.restapi.Supplier.SupplierDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserDto> getUsers();
}
