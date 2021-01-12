package com.pizzaservice.user.domain;

import java.util.List;

public interface UserRepository {
    void add(User user);
    User findById(Long userId);
    List<User> findAll();
    List<User> findByFullName(String firstName, String surname);
    List<User> findByFirstName(String firstName);
    List<User> findBySurname(String surname);
}
