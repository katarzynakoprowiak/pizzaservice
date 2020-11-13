package com.pizzaservice.service;

import com.pizzaservice.pizza.PizzaType;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
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

            String selectSql = "SELECT * FROM pizzaservicedb.order";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                List<Order> orders = new ArrayList<>();
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setOrderNumber(resultSet.getInt("order_id"));
                    order.addItems(getOrderPizzaByOrderId(order.getOrderNumber()));
                    int paymentOrderId = resultSet.getInt("payment_method_id");
                    order.setPaymentMethod(getById(paymentOrderId));
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

            String insertOrderSql = "INSERT INTO pizzaservicedb.order(payment_method_id, comment) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderSql,
                    Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setInt(1, getPaymentMethodId(order.getPaymentMethod()));
                preparedStatement.setString(2, order.getComment());

                preparedStatement.executeUpdate();

                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        autogenkey = resultSet.getInt(1);
                    }
                }

                addOrderPizza(autogenkey, order.getOrderedItems());
            }
        }

        return autogenkey;
    }

    private List<String> getOrderPizzaByOrderId(int orderNumber) throws SQLException{
        try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {

            String selectSql = "SELECT o.order_id, o.pizza_type_id, o.amount, p.type pizza_type FROM" +
                    "    order_pizza_type o INNER JOIN pizza_type p ON p.pizza_type_id = o.pizza_type_id WHERE order_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, orderNumber);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                List<PizzaType> pizzas = new ArrayList<>();
                while (resultSet.next()) {
                    PizzaType pizzaType = PizzaType.valueOf(resultSet.getString("pizza_type").toUpperCase());
                    pizzas.add(pizzaType);
                }
                return pizzas.stream().map(PizzaType::toString).collect(Collectors.toList());
            }
        }
    }

    private void addOrderPizza(int orderId, List<PizzaType> orderedItems) throws SQLException{

        try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {

            String insertOrderSql = "INSERT INTO order_pizza_type(order_id, pizza_type_id, amount) VALUES (?, ?, 1)";

            for (PizzaType pizzaType: orderedItems){
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderSql,
                        Statement.RETURN_GENERATED_KEYS)) {

                    preparedStatement.setInt(1, orderId);
                    preparedStatement.setInt(2, getPizzaTypeId(pizzaType));

                    preparedStatement.executeUpdate();
                }
            }
        }
    }

    private int getPizzaTypeId(PizzaType pizzaType) throws SQLException{
        int pizzaTypeId = 0;

        try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {

            String insertOrderSql = "SELECT * FROM pizza_type WHERE type=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderSql,
                    Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, pizzaType.toString());

                preparedStatement.executeQuery();

                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        pizzaTypeId = resultSet.getInt(1);
                    }
                }

                if(pizzaTypeId == 0){
                    pizzaTypeId = addPizzaType(pizzaType);
                }
            }
        }

        return pizzaTypeId;
    }

    private int addPizzaType(PizzaType pizzaType) throws SQLException {
        int autogenkey = 0;

        try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {

            String insertPizzaTypeSql = "INSERT INTO pizza_type(type) VALUES (?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertPizzaTypeSql,
                    Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, pizzaType.toString());

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

    private PaymentMethod getById(int id) throws SQLException{
        try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {

            String selectSql = "SELECT * FROM payment_method WHERE payment_method_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                PaymentMethod paymentMethod = null;

                while (resultSet.next()) {
                    paymentMethod = PaymentMethod.valueOf(resultSet.getString("name").toUpperCase().replace(" ", "_"));
                }
                return paymentMethod;
            }
        }
    }

    private int getPaymentMethodId(PaymentMethod paymentMethod) throws SQLException {
        try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {

            String selectSql = "SELECT payment_method_id FROM payment_method WHERE name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, paymentMethod.toString());

            int payment_id = 0;

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    payment_id = resultSet.getInt("payment_method_id");
                }
            }
            if (payment_id == 0){
                payment_id = addPaymentMethod(paymentMethod);
            }
            return payment_id;
        }
    }

    private int addPaymentMethod(PaymentMethod paymentMethod) throws SQLException {
        int autogenkey = 0;

        try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {

            String insertOrderSql = "INSERT INTO payment_method(name) VALUES (?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderSql,
                    Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, paymentMethod.toString());

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