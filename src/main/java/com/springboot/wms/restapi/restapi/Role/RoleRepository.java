package com.springboot.wms.restapi.restapi.Role;

import com.springboot.wms.restapi.restapi.Permission.Permission;
import com.springboot.wms.restapi.restapi.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query( "select r from Role r where id in :ids" )
    Set<Role> findByRoleIds(@Param("ids") Set<Long> rolesSet);
}
