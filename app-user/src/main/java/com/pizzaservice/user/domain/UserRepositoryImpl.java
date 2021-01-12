package com.pizzaservice.user.domain;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
class UserRepositoryImpl implements UserRepository {
    private static final String ANY_CHARACTER = "%";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(Long userId){
        User user = entityManager.find(User.class, new Long(userId));
        entityManager.detach(user);
        return user;
    }

    @Override
    public List<User> findAll(){
        return entityManager.createQuery("SELECT a FROM User a", User.class).getResultList();
    }

    @Override
    public List<User> findByFullName(String firstName, String surname){
        Query query = entityManager.createQuery("SELECT a FROM User a WHERE a.firstName like :firstName AND a.surname like :surname", User.class);
        query.setParameter("firstName", ANY_CHARACTER + firstName + ANY_CHARACTER);
        query.setParameter("surname", ANY_CHARACTER + surname + ANY_CHARACTER);

        return query.getResultList();
    }

    @Override
    public List<User> findByFirstName(String firstName){
        return findByFullName(firstName, "");
    }

    @Override
    public List<User> findBySurname(String surname){
        return findByFullName("", surname);
    }
}
