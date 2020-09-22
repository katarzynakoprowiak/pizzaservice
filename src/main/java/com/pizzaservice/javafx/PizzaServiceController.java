package com.pizzaservice.javafx;

import com.pizzaservice.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PizzaServiceController implements Initializable {
    OrderService orderService;

    @FXML
    TextField comment;

    @FXML
    TextField margheritaCount, capriciosaCount, funghiCount, calzoneCount;

    @FXML
    ComboBox<String> paymentTypeCombo;

    @FXML
    ListView<String> ordersListView = new ListView<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paymentTypeCombo.getItems().setAll(
                Stream.of(PaymentMethod.values())
                .map(Enum::toString)
                .collect(Collectors.toList()));

        resetInputFields();

        orderService = new OrderServiceImpl(OrderRepositoryImpl.getInstance());
    }

    private void resetInputFields() {
        resetPizzaCounts();
        resetPaymentCombo();
        resetCommentBox();
    }

    public void addMargherita(ActionEvent event){
        increasePizzaCount(margheritaCount);
    }

    public void subtractMargherita(ActionEvent event){
        decreasePizzaCount(margheritaCount);
    }

    public void addCapriciosa(ActionEvent event){
        increasePizzaCount(capriciosaCount);
    }

    public void subtractCapriciosa(ActionEvent event){
        decreasePizzaCount(capriciosaCount);
    }

    public void addFunghi(ActionEvent event){
        increasePizzaCount(funghiCount);
    }

    public void subtractFunghi(ActionEvent event){
        decreasePizzaCount(funghiCount);
    }

    public void addCalzone(ActionEvent event){
        increasePizzaCount(calzoneCount);
    }

    public void subtractCalzone(ActionEvent event){
        decreasePizzaCount(calzoneCount);
    }

    private void increasePizzaCount(TextField pizzaCount) {
        int count = Integer.parseInt(pizzaCount.getCharacters().toString());
        if (count<20){
        count++;}
        pizzaCount.setText(String.valueOf(count));
    }

    private void decreasePizzaCount(TextField pizzaCount) {
        int count = Integer.parseInt(pizzaCount.getCharacters().toString());
        if (count > 0){
        count--;}
        pizzaCount.setText(String.valueOf(count));
    }

    public void orderAction(ActionEvent event){
        List<String> pizzas = getPizzas();

        try{
            Order order = new Order.Builder()
                .addItems(pizzas)
                .paymentMethod(paymentTypeCombo.getValue())
                .comment(comment.getText())
                .build();

            orderService.takeOrder(order);

            resetInputFields();}
        catch (IllegalStateException exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText(exception.getMessage());

            alert.showAndWait();
        }
    }

    private List<String> getPizzas() {
        //TODO: the ugliest bit of code on earth
        List<String> pizzas = new ArrayList<>();
        for(int i = 0; i < Integer.parseInt(margheritaCount.getCharacters().toString()); i++){
            pizzas.add("margherita");
        }
        for(int i = 0; i < Integer.parseInt(capriciosaCount.getCharacters().toString()); i++){
            pizzas.add("capriciosa");
        }
        for(int i = 0; i < Integer.parseInt(funghiCount.getCharacters().toString()); i++){
            pizzas.add("funghi");
        }
        for(int i = 0; i < Integer.parseInt(calzoneCount.getCharacters().toString()); i++){
            pizzas.add("calzone");
        }
        return pizzas;
    }

    private void resetPizzaCounts() {
        margheritaCount.setText("0");
        capriciosaCount.setText("0");
        funghiCount.setText("0");
        calzoneCount.setText("0");
    }

    private void resetPaymentCombo(){
        paymentTypeCombo.getSelectionModel().selectFirst();
    }

    private void resetCommentBox(){
        comment.clear();
    }

    public void listAction(ActionEvent event){
        ObservableList<String> orders = FXCollections.observableList(
                orderService.getOrders().stream()
                        .map(object -> Objects.toString(object, null))
                        .collect(Collectors.toList()));

        if (orders.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("There are no orders");

            alert.showAndWait();
        }
        ordersListView.setItems(orders);
    }
}
