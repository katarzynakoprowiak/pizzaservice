package com.pizzaservice.user.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private static final Logger LOG = LogManager
            .getLogger(UserServiceImpl.class.getName());

    private UserRepository userRepository;
    private final Validator validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    @Transactional
    public User getById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    @Transactional
    public void add(User user) {
        Set<ConstraintViolation<User>> violations = getOrderViolations(user);

        if (violations.isEmpty()){
            user.setPriceModifier(new Random().nextDouble() + 0.5);
            userRepository.add(user);

            LOG.info("Added user {}", user.toString());
        } else {
            LOG.warn(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n")));
        }
    }

    @Override
    @Transactional
    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Set<ConstraintViolation<User>> getOrderViolations(User user) {
        return validator.validate(user);
    }

    @Override
    @Transactional
    public List<User> getByName(String firstName, String surname){
        return userRepository.findByFullName(firstName, surname);
    }
}
