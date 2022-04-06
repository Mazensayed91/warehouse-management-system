package com.springboot.wms.restapi.restapi.Permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query( "select p from Permission p where id in :ids" )
    Set<Permission> findByPermissionIds(@Param("ids") Set<Long> permissionSet);
}
