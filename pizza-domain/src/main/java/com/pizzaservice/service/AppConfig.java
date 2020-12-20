package com.pizzaservice.service;

import com.pizzaservice.repository.OrderRepository;
import com.pizzaservice.repository.OrderRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.pizzaservice" })
@ComponentScan({"com.pizzaservice"})
public class AppConfig {

    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepositoryImpl();
    }
}
