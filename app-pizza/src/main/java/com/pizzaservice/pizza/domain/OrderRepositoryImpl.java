package com.pizzaservice.pizza.domain;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
class OrderRepositoryImpl implements OrderRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Order order) {
        entityManager.persist(order);
    }

    @Override
    public Order findById(Long orderId){
        Order order = entityManager.find(Order.class, orderId);
        return order;
    }

    @Override
    public List<Order> findAll(){
        return entityManager.createQuery("SELECT a FROM Order a", Order.class).getResultList();
    }
}