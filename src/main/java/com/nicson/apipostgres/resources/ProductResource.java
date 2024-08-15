package com.nicson.apipostgres.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nicson.apipostgres.models.Product;
import com.nicson.apipostgres.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    public ProductService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
