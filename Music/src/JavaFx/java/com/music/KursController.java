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

public class KursController implements Initializable {
    @FXML
    private Button button_logout;
    @FXML
    private Label label_welcome;
    @FXML
    private  Button button_homepage;
    @FXML
    private  Button button_person;
    @FXML
    private Button button_bills;
    @FXML
    private Button button_learningplan;
    @FXML
    private Button button_instrument;
    @FXML
    private ComboBox combo2;
    @FXML
    private Button button_delete;
    @FXML
    private Button button_change;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_number;
    @FXML
    private TextField tf_fee;
    @FXML
    private TextField tf_teachrID;
    @FXML
    private TextField tf_infid;
    @FXML
    private TextField tf_newinf;
    @FXML
    private TextField tf_rstudent;
    @FXML
    private TextField tf_rcourse1;
    @FXML
    private TextField tf_rlehrer;
    @FXML
    private TextField tf_rcourse2;
    @FXML
    private Button button_regstudent;
    @FXML
    private Button button_regteacher;




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




        ObservableList<String> list2 = FXCollections.observableArrayList("Course Name","Course Number","Course Fee","Course Teacher ID");
        combo2.setItems(list2);
    }
    public void setUserInformation(String username){
        label_welcome.setText("Welcome \n"+ username+"!");
    }
}



