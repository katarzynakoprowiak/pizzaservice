package com.pizzaservice.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class PizzaServiceApplication extends Application {
    @Override
    public void start(Stage stage){
        try{
            FXMLLoader loader = new FXMLLoader(PizzaServiceController.class
                    .getResource("/fxml/pizzaservice.fxml"));

            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();}
        catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }

    }

    public static void main(String[] args) {
        launch();
    }

}
