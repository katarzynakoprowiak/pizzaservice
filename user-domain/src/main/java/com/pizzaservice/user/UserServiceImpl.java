package com.pizzaservice.user;

import com.pizzaservice.model.User;
import com.pizzaservice.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userService")
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
    public User get(Long userId) {
        return userRepository.get(userId);
    }

    @Override
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
    public List<User> getAll(){
        return userRepository.getAll();
    }

    public Set<ConstraintViolation<User>> getOrderViolations(User user) {
        return validator.validate(user);
    }

    @Override
    public List<User> searchByName(String firstName, String surname){
        return userRepository.searchByFullName(firstName, surname);
    }
}
