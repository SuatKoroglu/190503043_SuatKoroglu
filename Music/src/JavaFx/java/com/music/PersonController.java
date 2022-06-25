package com.music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PersonController implements Initializable {
    @FXML
    private Button button_logout;
    @FXML
    private Label label_welcome;
    @FXML
    private  Button button_homepage;
    @FXML
    private  Button button_kurs;
    @FXML
    private Button button_bills;
    @FXML
    private Button button_learningplan;
    @FXML
    private Button button_instrument;
    @FXML
    private ComboBox combo;
    @FXML
    private ComboBox combo2;
    @FXML
    private Label label_nummer;
    @FXML
    private Label label_preis;
    @FXML
    private Button button_delete;
    @FXML
    private Button button_change;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_surname;
    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_telefon;
    @FXML
    private TextField tf_mail;
    @FXML
    private TextField tf_adress;
    @FXML
    private TextField tf_number;
    @FXML
    private TextField tf_preis;
    @FXML
    private TextField tf_infid;
    @FXML
    private TextField tf_newinf;
    @FXML
    private Button button_create;

    public void setnumberInformation(){
        if (combo.getValue()=="Student") {
            label_nummer.setText("Student Number");
            label_preis.setText("Fee");
        }
        if (combo.getValue()=="Teacher") {
            label_nummer.setText("Personal Number");
            label_preis.setText("Salary");
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combo.setOnAction(event -> setnumberInformation());
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
        button_kurs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"kurs.fxml","Kurs",null);
            }
        });
        button_bills.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"rechnung.fxml","Bills",null);
            }
        });
        button_learningplan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"learningplan.fxml","Learning Plan",null);
            }
        });
        button_instrument.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"instrument.fxml","Instrument",null);
            }
        });
        button_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Delete Person?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    DBUtils.deleteperson(Integer.parseInt(tf_infid.getText()));
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The person is not deleted!");
                    alert1.showAndWait();
                }
            }
        });
        button_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Change Information?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    DBUtils.changeperson(Integer.parseInt(tf_infid.getText()),(String) combo2.getValue(),tf_newinf.getText());
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The person is not updated!");
                    alert1.showAndWait();
                }
            }
        });
        button_create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Create Person?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try {
                        if(combo.getValue()=="Student"){
                            DBUtils.createstudent(tf_name.getText(),tf_surname.getText(),Integer.parseInt(tf_id.getText()) ,Integer.parseInt(tf_telefon.getText()),tf_mail.getText(),tf_adress.getText(),Integer.parseInt(tf_number.getText()),Integer.parseInt(tf_preis.getText()));
                        }
                        else if (combo.getValue()=="Teacher"){
                            DBUtils.createteacher(tf_name.getText(),tf_surname.getText(),Integer.parseInt(tf_id.getText()) ,Integer.parseInt(tf_telefon.getText()),tf_mail.getText(),tf_adress.getText(),Integer.parseInt(tf_number.getText()),Integer.parseInt(tf_preis.getText()));

                        }else{
                            Alert alert1 = new Alert(Alert.AlertType.WARNING);
                            alert1.setTitle("Please Select!");
                            alert1.setContentText("Please Select Student or Teacher");
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
                    alert1.setContentText("The person is not created");
                    alert1.showAndWait();
                }
            }
        });




        ObservableList<String> list = FXCollections.observableArrayList("Student","Teacher");
        combo.setItems(list);
        ObservableList<String> list2 = FXCollections.observableArrayList("Name","Surname","IDnumber","Phone number","E-mail","Adress","Number","Preis");
        combo2.setItems(list2);
    }
    public void setUserInformation(String username){
        label_welcome.setText("Welcome \n"+ username+"!");
    }
}



