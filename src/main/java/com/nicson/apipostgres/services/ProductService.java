package com.nicson.apipostgres.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicson.apipostgres.models.Product;
import com.nicson.apipostgres.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }
}
