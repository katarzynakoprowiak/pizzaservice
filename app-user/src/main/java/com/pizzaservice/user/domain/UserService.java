package com.pizzaservice.user.domain;

import java.util.List;

public interface UserService {
    User getById(Long userId);
    void add(User user);
    List<User> getAll();
    List<User> getByName(String firstName, String surname);
}
