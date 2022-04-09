package com.springboot.wms.restapi.restapi.User;

import com.springboot.wms.restapi.restapi.Supplier.SupplierDto;
import com.springboot.wms.restapi.restapi.Supplier.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // create user
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    // get users
    @GetMapping
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }
}
