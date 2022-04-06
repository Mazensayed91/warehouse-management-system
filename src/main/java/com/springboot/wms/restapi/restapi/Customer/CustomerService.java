package com.springboot.wms.restapi.restapi.Customer;

import com.springboot.wms.restapi.restapi.Employee.EmployeeDto;

import java.util.List;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);
    List<CustomerDto> getCustomers();
}
