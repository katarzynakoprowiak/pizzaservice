package com.pizzaservice.service;

import com.pizzaservice.pizza.PizzaType;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository{
    private final String connectionUrl = "jdbc:mysql://localhost:3306/pizzaservicedb?serverTimezone=Europe/Moscow";
    private final String user = "Kasia";
    private final String password = "pierogi3";

    @Override
    public List<Order> getOrders() throws SQLException{

        try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {

            String selectSql = "SELECT * FROM orders";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                List<Order> orders = new ArrayList<>();
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setOrderNumber(resultSet.getInt("id"));
                    order.addItems(Arrays.asList(resultSet.getString("ordered_items")
                            .split(", ")));
                    order.setPaymentMethod(PaymentMethod.valueOf(resultSet.getString("payment_method").toUpperCase().replace(" ", "_")));
                    order.setComment(resultSet.getString("comment"));
                    orders.add(order);
                }
                return orders;
            }
        }
    }

    @Override
    public int addOrder(Order order) throws SQLException {
        int autogenkey = 0;

        try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {

            String insertOrderSql = "INSERT INTO orders(ordered_items, payment_method, comment) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderSql,
                    Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, order.getOrderedItems().stream()
                        .map(PizzaType::toString).collect(Collectors.joining(", ")));
                preparedStatement.setString(2, order.getPaymentMethod().toString());
                preparedStatement.setString(3, order.getComment());

                preparedStatement.executeUpdate();

                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        autogenkey = resultSet.getInt(1);
                    }
                }
            }
        }

        return autogenkey;
    }
}