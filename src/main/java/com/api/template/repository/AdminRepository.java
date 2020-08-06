package com.api.template.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.template.model.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long>{}

