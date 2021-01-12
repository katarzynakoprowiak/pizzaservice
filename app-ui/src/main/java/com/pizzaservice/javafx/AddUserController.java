package com.pizzaservice.javafx;

import com.pizzaservice.user.domain.User;
import com.pizzaservice.user.domain.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {
    private ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);

    private static final Logger LOG = LogManager.getLogger(AddUserController.class.getName());

    private UserService service;

    @FXML
    private TextField firstName, surname;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        service = appContext.getBean(UserService.class);

        resetInputFields();
    }

    public void addAction(){
        User user = new User(firstName.getText(), surname.getText());

        service.add(user);

        resetInputFields();
    }


    public void backAction(){
        try{
            Stage stage = (Stage) firstName.getScene().getWindow();
            Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/selectuser.fxml"));
            stage.getScene().setRoot(newRoot);
        } catch (IOException ioe){
            LOG.warn("Exception switching windows - Back button");
        }
    }

    private void resetInputFields() {
        firstName.clear();
        surname.clear();
    }

}
