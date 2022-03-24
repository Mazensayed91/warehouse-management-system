package com.springboot.wms.restapi.restapi.repository;

import com.springboot.wms.restapi.restapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //public boolean existsByNumber(String number);

    //public List<Employee> findByNumber(String number);

    @Query("select max(e.id) from Employee e")
    public Long findMaxId();

    public List<Employee> findByEmployee_Order_OrderLine_SkuQuantityUnit_Name(String sku_name);


}
