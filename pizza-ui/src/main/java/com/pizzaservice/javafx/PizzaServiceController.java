package com.pizzaservice.javafx;

import com.pizzaservice.pizza.Pizza;
import com.pizzaservice.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.converter.IntegerStringConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.validation.ConstraintViolation;
import java.net.URL;
import java.util.*;

import static com.pizzaservice.utils.PizzaUtils.DEFAULT_COUNT;
import static java.util.stream.Collectors.toList;

public class PizzaServiceController implements Initializable {
    ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
    OrderService orderService;
    PizzaService pizzaService;

    @FXML
    TextField comment;

    @FXML
    TextField margheritaCount, capriciosaCount, funghiCount, calzoneCount;

    @FXML
    ComboBox<PaymentMethod> paymentTypeCombo;

    @FXML
    ListView<Order> ordersListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderService = appContext.getBean("orderService", OrderService.class);
        pizzaService = appContext.getBean("pizzaService", PizzaService.class);

        paymentTypeCombo.getItems().setAll(PaymentMethod.values());

        margheritaCount.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        capriciosaCount.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        funghiCount.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        calzoneCount.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

        resetInputFields();
    }

    public void orderAction(){
        List<String> pizzas = getPizzas();

        Order order = new Order();
        order.addItems(pizzas);
        order.setPaymentMethod(paymentTypeCombo.getValue());
        order.setComment(comment.getText());

        orderService.takeOrder(order);

        resetInputFields();
    }

    public void listAction(){
        ObservableList<Order> orders = FXCollections.observableList(
                orderService.getOrders());

        if (orders.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("There are no orders");

            alert.showAndWait();
        }
        ordersListView.setItems(orders);
    }

    public void prepareAction(){
        Order selectedOrder = ordersListView.getSelectionModel().getSelectedItem();

        if (selectedOrder != null){
            List<Pizza> preparedPizzas = pizzaService.makePizza(selectedOrder);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Prepared order");
            alert.setHeaderText(String.format("Order #%d", selectedOrder.getOrderNumber()));
            alert.setContentText(String.join("\n",
                    preparedPizzas.stream()
                            .map(Pizza::toString)
                            .collect(toList())));

            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Please select order to prepare and try again.");

            alert.showAndWait();
        }
    }

    public void addMargherita(){
        increasePizzaCount(margheritaCount);
    }

    public void subtractMargherita(){
        decreasePizzaCount(margheritaCount);
    }

    public void addCapriciosa(){
        increasePizzaCount(capriciosaCount);
    }

    public void subtractCapriciosa(){
        decreasePizzaCount(capriciosaCount);
    }

    public void addFunghi(){
        increasePizzaCount(funghiCount);
    }

    public void subtractFunghi(){
        decreasePizzaCount(funghiCount);
    }

    public void addCalzone(){
        increasePizzaCount(calzoneCount);
    }

    public void subtractCalzone(){
        decreasePizzaCount(calzoneCount);
    }

    private List<String> getPizzas() {
        List<String> pizzas = new ArrayList<>();
        getPizzasFromTextField(pizzas, margheritaCount, "margherita");
        getPizzasFromTextField(pizzas, capriciosaCount, "capriciosa");
        getPizzasFromTextField(pizzas, funghiCount, "funghi");
        getPizzasFromTextField(pizzas, calzoneCount, "calzone");
        return pizzas;
    }

    private void getPizzasFromTextField(List<String> pizzas, TextField field, String pizzaName) {
        for (int i = 0; i < Integer.parseInt(field.getCharacters().toString()); i++) {
            pizzas.add(pizzaName);
        }
    }

    private void resetInputFields() {
        resetPizzaCounts();
        resetPaymentCombo();
        resetCommentBox();
    }

    private void resetPizzaCounts() {
        margheritaCount.setText(DEFAULT_COUNT);
        capriciosaCount.setText(DEFAULT_COUNT);
        funghiCount.setText(DEFAULT_COUNT);
        calzoneCount.setText(DEFAULT_COUNT);
    }

    private void resetPaymentCombo(){
        paymentTypeCombo.getSelectionModel().selectFirst();
    }

    private void resetCommentBox(){
        comment.clear();
    }

    private void increasePizzaCount(TextField pizzaCount) {
        int count = Integer.parseInt(pizzaCount.getCharacters().toString());
        if (count < 20){
            count++;}
        pizzaCount.setText(String.valueOf(count));
    }

    private void decreasePizzaCount(TextField pizzaCount) {
        int count = Integer.parseInt(pizzaCount.getCharacters().toString());
        if (count > 0){
            count--;}
        pizzaCount.setText(String.valueOf(count));
    }
}
