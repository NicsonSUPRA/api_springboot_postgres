package com.nicson.apipostgres.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nicson.apipostgres.models.Category;
import com.nicson.apipostgres.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Category> insert(@RequestBody Category category) {
        System.out.println("nome da categoria: " + category.getName());
        Category categorySaved = service.insert(category);
        return ResponseEntity.ok().body(categorySaved);

    }
}
