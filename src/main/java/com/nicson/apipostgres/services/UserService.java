package com.nicson.apipostgres.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nicson.apipostgres.models.User;
import com.nicson.apipostgres.repositories.UserRepository;
import com.nicson.apipostgres.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        try {
            Optional<User> user = repository.findById(id);
            return user.get();
        } catch (RuntimeException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public List<User> findByName(String name) {
        return repository.findByNameIgnoreCaseContaining(name);
    }

    public User insert(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        repository.save(user);
        return user;
    }

    public User obterPorLogin(String login) {

    public User findUserByLogin(String login) {
        return repository.findByEmail(login);
    }
}
