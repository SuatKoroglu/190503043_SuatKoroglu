package com.music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class LearningPlanController implements Initializable {
    @FXML
    private Button button_bill;

    @FXML
    private Button button_changepassword;

    @FXML
    private Button button_changeusername;

    @FXML
    private Button button_homepage;

    @FXML
    private Button button_instrument;

    @FXML
    private Button button_kurs;

    @FXML
    private Button button_logout;

    @FXML
    private Button button_person;

    @FXML
    private Label label_welcome;

    @FXML
    private PasswordField tf_newp1;

    @FXML
    private PasswordField tf_newp2;

    @FXML
    private TextField tf_newu;

    @FXML
    private PasswordField tf_p1;

    @FXML
    private PasswordField tf_p2;

    @FXML
    private TextField tf_un1;

    @FXML
    private TextField tf_un2;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"sample.fxml","Log in!",null);
            }
        });
        button_homepage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"homepage.fxml","Homepage",null);
            }
        });
        button_person.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"person.fxml","Person",null);
            }
        });
        button_kurs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"kurs.fxml","Kurs",null);
            }
        });
        button_bill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"rechnung.fxml","Bills",null);
            }
        });
        button_instrument.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"instrument.fxml","Instrument",null);
            }
        });
        button_changepassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Change Password?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try{
                        if (tf_newp1.getText().equals(tf_newp2.getText())){
                            DBUtils.changepassword(tf_un1.getText(),tf_p1.getText(),tf_newp1.getText());
                        }else{
                            Alert alert1 = new Alert(Alert.AlertType.WARNING);
                            alert1.setTitle("Warning!");
                            alert1.setContentText("Password did not match");
                            alert1.showAndWait();
                        }

                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The Instrument is not created!");
                    alert1.showAndWait();
                }
            }
        });
        button_changeusername.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Change Password?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try{
                        DBUtils.changeusername(tf_un2.getText(),tf_p2.getText(),tf_newu.getText());

                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The Instrument is not created!");
                    alert1.showAndWait();
                }
            }
        });





    }
    public void setUserInformation(String username){
        label_welcome.setText("Welcome \n"+ username+"!");
    }
}



