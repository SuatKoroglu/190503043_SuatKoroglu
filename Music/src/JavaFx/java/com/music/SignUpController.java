package com.music;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button button_signup;
    @FXML
    private Button button_log_in;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField tf_password1;
    @FXML
    private PasswordField tf_password2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if (tf_password1.getText().equals(tf_password2.getText())) {
                   if (!tf_username.getText().trim().isEmpty() && !tf_password1.getText().trim().isEmpty()) {
                       DBUtils.signUpUser(event, tf_username.getText(), tf_password1.getText());
                   } else {
                       System.out.println("Please fill in all information");
                       Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setContentText("Please fill in all information to sign up!");
                       alert.show();
                   }
               }else{
                   System.out.println("Passwords are not matched");
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setContentText("Passwords are not matched!");
                   alert.show();
               }
            }
        });

        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"sample.fxml","Log in!",null);
            }
        });
    }
}
