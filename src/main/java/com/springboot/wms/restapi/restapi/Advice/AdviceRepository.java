package com.springboot.wms.restapi.restapi.Advice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AdviceRepository extends JpaRepository<Advice, Long> {

    @Query("SELECT a.id FROM Advice a where a.status = :status")
    List<Long> findByStatus(@Param("status") Advice.Status status);

}
