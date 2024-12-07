package com.nicson.apipostgres.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicson.apipostgres.models.Category;
import com.nicson.apipostgres.repositories.CategoryRepository;
import com.nicson.apipostgres.security.SecurityService;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private SecurityService securityService;

    public List<Category> findAll() {
        System.out.println(securityService.obterUsuarioLogado().getEmail());
        return repository.findAll();
    }

    public Category insert(Category category) {
        Category categorySaved = repository.save(category);
        System.out.println(securityService.obterUsuarioLogado());
        return categorySaved;
    }
}
