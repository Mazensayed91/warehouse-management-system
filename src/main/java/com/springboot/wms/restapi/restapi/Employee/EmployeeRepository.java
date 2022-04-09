package com.springboot.wms.restapi.restapi.Employee;

import com.springboot.wms.restapi.restapi.Employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //public List<Employee> findByOrders_OrderLines_SkuQuantityUnit_Name(String sku_name);
}
