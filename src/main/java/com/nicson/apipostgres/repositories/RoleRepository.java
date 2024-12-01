package com.nicson.apipostgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nicson.apipostgres.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
