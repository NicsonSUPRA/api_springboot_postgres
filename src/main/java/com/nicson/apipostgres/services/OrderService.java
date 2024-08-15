package com.nicson.apipostgres.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicson.apipostgres.models.Order;
import com.nicson.apipostgres.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }
}
