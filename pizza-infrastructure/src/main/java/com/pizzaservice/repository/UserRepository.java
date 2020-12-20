package com.pizzaservice.repository;

import com.pizzaservice.model.User;

import java.util.List;

public interface UserRepository {
    void add(User user);
    User get(Long userId);
    User findDbInstanceByName(User user);
    List<User> getAll();
    List<User> searchByFullName(String firstName, String surname);
    List<User> searchByFirstName(String firstName);
    List<User> searchBySurname(String surname);
}
