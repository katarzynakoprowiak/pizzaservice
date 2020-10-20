package com.pizzaservice.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

public class PizzaServiceApplication extends Application {
    private static final Logger log = LogManager.getLogger(PizzaServiceApplication.class.getName());

    @Override
    public void start(Stage stage){
        try{
            FXMLLoader loader = new FXMLLoader(PizzaServiceController.class
                    .getResource("/fxml/pizzaservice.fxml"));

            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();}
        catch (IOException ioe){
            log.error("Exception loading app", ioe);
        }

    }

    public static void main(String[] args) {
        launch();
    }

}
