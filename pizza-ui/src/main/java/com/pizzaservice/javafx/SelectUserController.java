package com.pizzaservice.javafx;

import com.pizzaservice.model.User;
import com.pizzaservice.user.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectUserController implements Initializable {
    ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);

    private static final Logger LOG = LogManager.getLogger(SearchUserController.class.getName());

    @Autowired
    private UserService service;

    @FXML
    ComboBox<User> userCombo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        service = appContext.getBean("userService", UserService.class);

        userCombo.getItems().setAll(service.getAll());
    }

    public void selectAction(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pizzaservice.fxml"));

            Stage stage = (Stage) userCombo.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

            PizzaServiceController controller = loader.getController();
            User user = userCombo.getValue();

            controller.setUser(user);

            stage.show();
        } catch (IOException ioe){
            LOG.warn("Exception switching windows - Select button", ioe);
        }
    }

    public void addAction(){
        try{
            Stage stage = (Stage) userCombo.getScene().getWindow();
            Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/adduser.fxml"));
            stage.getScene().setRoot(newRoot);
        } catch (IOException ioe) {
            LOG.warn("Exception switching windows - Add button", ioe);
        }
    }

    public void searchAction(){
        try{
            Stage stage = (Stage) userCombo.getScene().getWindow();
            Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/searchuser.fxml"));
            stage.getScene().setRoot(newRoot);
        } catch (IOException ioe) {
            LOG.warn("Exception switching windows - Add button", ioe);
        }
    }

}
