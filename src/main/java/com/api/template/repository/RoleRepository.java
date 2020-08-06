package com.api.template.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.template.enums.RoleName;
import com.api.template.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
    Role findByAuthority(RoleName name);
}

