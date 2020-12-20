package com.pizzaservice.user;

import com.pizzaservice.model.User;

import java.util.List;

public interface UserService {
    User get(Long userId);
    void add(User user);
    List<User> getAll();
    List<User> searchByName(String firstName, String surname);
}
