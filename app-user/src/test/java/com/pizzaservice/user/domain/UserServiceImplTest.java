package com.pizzaservice.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    private UserServiceImpl service;
    private UserRepository mockRepository;

    @BeforeEach
    void setup(){
        mockRepository = mock(UserRepository.class);
        service = new UserServiceImpl(mockRepository);
    }

    @Test
    void shouldAddUser(){
        //given
        User user = new User("Firstname", "Surname", 1.2);

        when(mockRepository.findAll()).thenReturn(Arrays.asList(user));

        //when
        service.add(user);

        //then
        assertThat(service.getAll(), hasItem(user));
    }


    @Test
    void shouldReturnUsers(){
        //given
        User user = new User("Firstname", "Surname", 1.1);
        User anotherUser = new User("Name", "Lastname", 1.2);

        when(mockRepository.findAll()).thenReturn(Arrays.asList(user, anotherUser));

        //when
        service.add(user);
        service.add(anotherUser);

        //then
        assertThat(service.getAll(), hasItems(user, anotherUser));
    }

    @Test
    void shouldReturnViolationIfEmptyUserIsAdded(){
        User user = new User();

        Set<ConstraintViolation<User>> userViolations = service.getOrderViolations(user);

        List<String> messages = userViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        assertThat(messages, hasItems("First name cannot be empty", "Surname cannot be empty"));
    }

    @Test
    void shouldFindUserByFirstName(){
        //given
        User matchingUser = new User("test", "Surname", 1.0);
        User anotherMatchingUser = new User("Testing", "Lastname", 1.0);
        User notMatchingUser = new User("Firstname", "Surname", 1.0);
        User anotherNotMatchingUser = new User("Name", "Lastname", 1.0);
        
        when(mockRepository.findByFullName("Test", "")).thenReturn(Arrays.asList(matchingUser, anotherMatchingUser));

        service.add(matchingUser);
        service.add(anotherMatchingUser);
        service.add(notMatchingUser);
        service.add(anotherNotMatchingUser);

        //when
        List<User> results = service.getByName("Test", "");

        //then
        assertEquals(2, results.size());
        assertThat(results, hasItems(matchingUser, anotherMatchingUser));
    }

    @Test
    void shouldFindUserBySurname(){
        //given
        User matchingUser = new User("Firstname", "test", 1.0);
        User anotherMatchingUser = new User("Name", "Testing", 1.0);
        User notMatchingUser = new User("Firstname", "Surname", 1.0);
        User anotherNotMatchingUser = new User("Name", "Lastname", 1.0);

        when(mockRepository.findByFullName("","Test")).thenReturn(Arrays.asList(matchingUser, anotherMatchingUser));

        service.add(matchingUser);
        service.add(anotherMatchingUser);
        service.add(notMatchingUser);
        service.add(anotherNotMatchingUser);

        //when
        List<User> results = service.getByName("", "Test");

        //then
        assertEquals(2, results.size());
        assertThat(results, hasItems(matchingUser, anotherMatchingUser));
    }
}