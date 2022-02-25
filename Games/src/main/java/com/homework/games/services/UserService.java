package com.homework.games.services;

import com.homework.games.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserByUsername(String username);

    void deleteAll();

    List<User> findAll();

    void add(User user);

    void update(User user, Long id);
}
