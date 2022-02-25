package com.homework.games.services.impl;

import com.homework.games.entities.User;
import com.homework.games.repo.UserRepository;
import com.homework.games.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user, Long id) {
        User currentUser = userRepository.findById(id).get();

        currentUser.setId(user.getId());
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setActive(user.isActive());
        currentUser.setRoles(user.getRoles());
        currentUser.setRating(user.getRating());
        currentUser.setAverage(user.getAverage());
        currentUser.setStep(user.getStep());
        currentUser.setNumberOfGamesPlayed(user.getNumberOfGamesPlayed());

        userRepository.save(currentUser);
    }
}
