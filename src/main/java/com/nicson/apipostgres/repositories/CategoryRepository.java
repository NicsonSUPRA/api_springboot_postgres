package com.nicson.apipostgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nicson.apipostgres.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
