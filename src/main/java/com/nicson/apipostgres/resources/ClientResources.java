package com.nicson.apipostgres.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nicson.apipostgres.models.Client;
import com.nicson.apipostgres.services.ClientService;

@RestController
@RequestMapping("clients")
public class ClientResources {

    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody Client client) {
        clientService.insert(client);
    }
}
