package com.pizzaservice.user.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration (classes = {AppConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
class UserRepositoryImplTest {
    @Autowired
    private UserRepository repository;

    @Test
    void shouldAddUser(){
        //given
        User user = new User("Firstname", "Surname", 1.0);

        //when
        repository.add(user);

        //then
        assertThat(repository.findAll(), hasItem(user));
    }


    @Test
    void shouldReturnUsers(){
        //given
        User user = new User("Firstname", "Surname", 1.0);
        User anotherUser = new User("Name", "Lastname", 0.95);

        //when
        repository.add(user);
        repository.add(anotherUser);

        //then
        assertThat(repository.findAll(), hasItems(user, anotherUser));
    }

    @Test
    void shouldFindUserByFirstName(){
        //given
        User matchingUser = new User("test", "Surname", 1.0);
        User anotherMatchingUser = new User("Testing", "Lastname", 1.0);
        User notMatchingUser = new User("Firstname", "Surname", 1.0);
        User anotherNotMatchingUser = new User("Name", "Lastname", 1.0);

        repository.add(matchingUser);
        repository.add(anotherMatchingUser);
        repository.add(notMatchingUser);
        repository.add(anotherNotMatchingUser);

        //when
        List<User> results = repository.findByFirstName("test");

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

        repository.add(matchingUser);
        repository.add(anotherMatchingUser);
        repository.add(notMatchingUser);
        repository.add(anotherNotMatchingUser);

        //when
        List<User> results = repository.findBySurname("Test");

        //then
        assertEquals(2, results.size());
        assertThat(results, hasItems(matchingUser, anotherMatchingUser));
    }

    @Test
    void shouldFindUserByFullName(){
        //given
        User matchingUser = new User("Test", "Testing", 1.0);
        User notMatchingUser = new User("T3sting", "Test", 2.0);

        repository.add(matchingUser);
        repository.add(notMatchingUser);

        //when
        List<User> results = repository.findByFullName("test", "test");

        //then
        assertEquals(1, results.size());
        assertThat(results, hasItem(matchingUser));
    }
}