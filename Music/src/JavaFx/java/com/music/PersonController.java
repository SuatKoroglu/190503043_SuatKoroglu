package com.music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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


    public void setnumberInformation(){
        if (combo.getValue()=="Student") {
            label_nummer.setText("Student Number");
            label_preis.setText("Gebühr");
        }
        if (combo.getValue()=="Teacher") {
            label_nummer.setText("Personal Number");
            label_preis.setText("Gehalt");
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
                DBUtils.changeScene(event,"person.fxml","Kurs",null);
            }
        });
        button_bills.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"person.fxml","Bills",null);
            }
        });
        button_learningplan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"person.fxml","Learning Plan",null);
            }
        });
        button_instrument.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"person.fxml","Instrument",null);
            }
        });
        button_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Delete Person?");
                alert.show();
            }
        });
        button_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Change Information?");
                alert.show();
            }
        });




        ObservableList<String> list = FXCollections.observableArrayList("Student","Teacher");
        combo.setItems(list);
        ObservableList<String> list2 = FXCollections.observableArrayList("Name","Nachname","IdNummer","Telefonnummer","e-mail","Adresse","Nummer","Preis");
        combo2.setItems(list2);
    }
    public void setUserInformation(String username){
        label_welcome.setText("Welcome \n"+ username+"!");
    }
}



