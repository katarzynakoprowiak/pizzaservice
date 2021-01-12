package com.pizzaservice.javafx;

import com.pizzaservice.pizza.domain.*;
import com.pizzaservice.pizza.application.PizzaPriceCalculator;
import com.pizzaservice.user.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.pizzaservice.pizza.utils.PizzaUtils.DEFAULT_COUNT;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

public class PizzaServiceController implements Initializable {
    private ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);

    private OrderService orderService;
    private PizzaService pizzaService;
    private PizzaPriceCalculator calculator;
    private User user;

    @FXML
    private Label userDetail;

    @FXML
    private Label margheritaPrice, capriciosaPrice, funghiPrice, calzonePrice;

    @FXML
    private TextField comment;

    @FXML
    private TextField margheritaCount, capriciosaCount, funghiCount, calzoneCount;

    @FXML
    private ComboBox<PaymentMethod> paymentTypeCombo;

    @FXML
    private ListView<Order> ordersListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderService = appContext.getBean(OrderService.class);
        pizzaService = appContext.getBean(PizzaService.class);
        calculator = appContext.getBean(PizzaPriceCalculator.class);

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
        order.setUserId(user.getId());

        orderService.takeOrder(order);

        resetInputFields();
    }

    public void switchUserAction() throws IOException {
        Stage primaryStage = (Stage) userDetail.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/selectuser.fxml"));
        primaryStage.getScene().setRoot(newRoot);
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
            alert.setHeaderText(String.format("Order #%d", selectedOrder.getId()));
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

    public void setUser(User user) {
        this.user = user;
        userDetail.setText(getUserDetailText());
        setPizzaPrices();
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

    private String getUserDetailText() {
        if (isNull(user)){
            return "No user selected";
        }
        return String.format("Logged as %s", user);
    }

    private void setPizzaPrices() {
        //TODO can't be left like this, should not create pizza instances like this
        margheritaPrice.setText(String.format("%.2f", calculator.calculatePrice(Margherita.builder().build(), user.getPriceModifier())));
        capriciosaPrice.setText(String.format("%.2f", calculator.calculatePrice(Capriciosa.builder().build(), user.getPriceModifier())));
        funghiPrice.setText(String.format("%.2f", calculator.calculatePrice(Funghi.builder().build(), user.getPriceModifier())));
        calzonePrice.setText(String.format("%.2f", calculator.calculatePrice(Capriciosa.builder().build(), user.getPriceModifier())));
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
