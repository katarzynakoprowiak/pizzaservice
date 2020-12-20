package com.pizzaservice.repository;

import com.pizzaservice.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public User get(Long userId){
        User user = entityManager.find(User.class, new Long(userId));
        entityManager.detach(user);
        return user;
    }

    @Override
    @Transactional
    public User findDbInstanceByName(User user) {
        //TODO: druciarstwa ciag dalszy
        Query query = entityManager.createQuery("SELECT a FROM User a WHERE a.firstName = :firstName AND a.surname = :surname");
        query.setParameter("firstName", user.getFirstName());
        query.setParameter("surname", user.getSurname());

        if (query.getResultList().size() > 0){
            return (User) query.getSingleResult();
        }

        return null;
    }

    @Override
    @Transactional
    public List<User> getAll(){
        return entityManager.createQuery("SELECT a FROM User a", User.class).getResultList();
    }

    @Override
    @Transactional
    public List<User> searchByFullName(String firstName, String surname){
        //TODO: there is a method findByName and now we have searchByName?
        Query query = entityManager.createQuery("SELECT a FROM User a WHERE a.firstName like :firstName AND a.surname like :surname");
        query.setParameter("firstName", "%" + firstName + "%");
        query.setParameter("surname", "%" + surname + "%");

        return query.getResultList();
    }

    @Override
    @Transactional
    public List<User> searchByFirstName(String firstName){
        return searchByFullName(firstName, "");
    }

    @Override
    @Transactional
    public List<User> searchBySurname(String surname){
        return searchByFullName("", surname);
    }
}
