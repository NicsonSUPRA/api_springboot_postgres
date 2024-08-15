package com.nicson.apipostgres.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nicson.apipostgres.models.Order;
import com.nicson.apipostgres.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Order>> findAll() {
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
