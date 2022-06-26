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

public class RechnungController implements Initializable {
    @FXML
    private Button button_logout;
    @FXML
    private Label label_welcome;
    @FXML
    private  Button button_homepage;
    @FXML
    private  Button button_kurs;
    @FXML
    private Button button_person;
    @FXML
    private Button button_learningplan;
    @FXML
    private Button button_instrument;
    @FXML
    private TextField tf_nummer;
    @FXML
    private TextField tf_amount;
    @FXML
    private TextField tf_studid;
    @FXML
    private TextField tf_kursnummer;
    @FXML
    private DatePicker tf_rechnnugsdatum;
    @FXML
    private Button button_create;
    @FXML
    private TextField tf_paidrechnung;
    @FXML
    private DatePicker tf_payday;
    @FXML
    private Button button_save;
    @FXML
    private ComboBox combo;
    @FXML
    private Button button_delete;
    @FXML
    private Button button_change;
    @FXML
    private TextField tf_infid;
    @FXML
    private TextField tf_newinf;



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
        button_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Change Information?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try{
                        if(combo.getValue()!="Information"){
                            DBUtils.changebill(Integer.parseInt(tf_infid.getText()),(String) combo.getValue(),tf_newinf.getText());
                        }else{
                            Alert alert1 = new Alert(Alert.AlertType.WARNING);
                            alert1.setTitle("Please Select!");
                            alert1.setContentText("Please Select Info");
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
                    alert1.setContentText("The bill is not Updated!");
                    alert1.showAndWait();
                }
            }
        });
        button_save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Save payment?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try {
                        DBUtils.paybill(Integer.parseInt(tf_paidrechnung.getText()),tf_payday.getValue().toString());
                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The bill has not been paid!");
                    alert1.showAndWait();
                }
            }
        });
        button_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Delete Bill?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try {
                        DBUtils.deletebill(Integer.parseInt(tf_infid.getText()));
                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The bill is not deleted!");
                    alert1.showAndWait();
                }
            }
        });
        button_create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Create Bill?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try{
                        DBUtils.createbill(Integer.parseInt(tf_nummer.getText()),Integer.parseInt(tf_amount.getText()),Integer.parseInt(tf_studid.getText()),Integer.parseInt(tf_kursnummer.getText()), tf_rechnnugsdatum.getValue().toString());
                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The bill is not created!");
                    alert1.showAndWait();
                }
            }
        });




        ObservableList<String> list = FXCollections.observableArrayList("Bill Number","Total Amount","Date of invoice");
        combo.setItems(list);

    }
    public void setUserInformation(String username){
        label_welcome.setText("Welcome \n"+ username+"!");
    }
}



