package com.nicson.apipostgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nicson.apipostgres.models.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByNameIgnoreCaseContaining(String name);

    public User findByEmail(String email);
}
