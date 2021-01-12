package com.pizzaservice.javafx;

import com.pizzaservice.user.domain.User;
import com.pizzaservice.user.domain.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchUserController implements Initializable {
    private ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);

    private static final Logger LOG = LogManager.getLogger(SearchUserController.class.getName());

    private UserService service;

    @FXML
    private ListView<User> userListView;

    @FXML
    private TextField firstNameField, surnameField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        service = appContext.getBean(UserService.class);
    }

    public void searchAction(){
        String firstName = firstNameField.getText();
        String surname = surnameField.getText();

        ObservableList<User> users = FXCollections.observableList(
                service.getByName(firstName, surname));

        userListView.setItems(users);
    }

    public void selectAction(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pizzaservice.fxml"));

            Stage stage = (Stage) userListView.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

            PizzaServiceController controller = loader.getController();
            User user = userListView.getSelectionModel().getSelectedItem();

            controller.setUser(user);

            stage.show();
        } catch (IOException ioe){
            LOG.warn("Exception switching windows - Select button", ioe);
        }
    }

    public void backAction(){
        try{
            Stage stage = (Stage) userListView.getScene().getWindow();
            Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/selectuser.fxml"));
            stage.getScene().setRoot(newRoot);
        } catch (IOException ioe){
            LOG.warn("Exception switching windows - Back button", ioe);
        }
    }
}
