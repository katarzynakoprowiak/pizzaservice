package com.pizzaservice.repository;

import com.pizzaservice.model.Order;
import com.pizzaservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.util.Objects.isNull;

@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public void add(Order order) {

        //TODO: straszne druciarstwo
        User user = userRepository.findDbInstanceByName(order.getUser());

        if (isNull(user)) {
            userRepository.add(order.getUser());
        } else {
            order.setUser(user);
        }

        entityManager.persist(order);
    }

    @Override
    @Transactional
    public Order get(Long orderId){
        Order order = entityManager.find(Order.class, new Long(orderId));
        entityManager.detach(order);
        return order;
    }

    @Override
    @Transactional
    public List<Order> getAll(){
        return entityManager.createQuery("SELECT a FROM Order a", Order.class).getResultList();
    }
}