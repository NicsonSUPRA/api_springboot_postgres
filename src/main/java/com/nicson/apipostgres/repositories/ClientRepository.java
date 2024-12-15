package com.nicson.apipostgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nicson.apipostgres.models.Client;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    public Client findByClientId(String clientId);
}
