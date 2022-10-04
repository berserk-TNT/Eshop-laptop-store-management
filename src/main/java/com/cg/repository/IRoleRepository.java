package com.cg.repository;

import com.cg.model.Role;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.RoleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByRoleName(String roleName);

    @Query("SELECT NEW com.cg.model.dto.RoleDTO(" +
                   "r.id, " +
                   "r.roleName) " +
                   "FROM Role AS r")
    List<RoleDTO> findAllRoleDTO();
}
