package com.nicson.apipostgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nicson.apipostgres.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
