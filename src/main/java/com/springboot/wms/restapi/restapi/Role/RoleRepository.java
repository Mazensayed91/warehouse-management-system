package com.springboot.wms.restapi.restapi.Role;

import com.springboot.wms.restapi.restapi.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
