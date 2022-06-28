package com.music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

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
    private TextField tf_newinf;

    @FXML
    private Button button_regstudent;
    @FXML
    private Button button_regteacher;
    @FXML
    private Button button_create;
    @FXML
    private Button button_unregt;
    @FXML
    private Button button_unregs;
    @FXML
    private TableColumn<Kurs, Integer> col_camount;

    @FXML
    private TableColumn<Kurs, String> col_cname;

    @FXML
    private TableColumn<Kurs, Integer> col_cnummer;

    @FXML
    private TableColumn<Kurs, Integer>col_cteacher;

    @FXML
    private TableView<Kurs> table_course;
    @FXML
    private ChoiceBox<Integer> cho1;
    @FXML
    private ChoiceBox<Integer> cho2;

    @FXML
    private ChoiceBox<Integer> cho3;

    @FXML
    private ChoiceBox<Integer> cho4;

    @FXML
    private ChoiceBox<Integer> cho5;

    @FXML
    private ChoiceBox<Integer> cho6;

    ObservableList<Kurs> listm;
    ObservableList<Lehrer> listk;
    ObservableList<Studieren> lista;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_cname.setCellValueFactory(new PropertyValueFactory<Kurs,String>("kursname"));
        col_cnummer.setCellValueFactory(new PropertyValueFactory<Kurs,Integer>("kursnummer"));
        col_camount.setCellValueFactory(new PropertyValueFactory<Kurs,Integer>("betrag"));
        col_cteacher.setCellValueFactory(new PropertyValueFactory<Kurs,Integer>("kurslehrerid"));


        listm = DBUtils.getdatakurs();
        table_course.setItems(listm);


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
                alert.setContentText("Delete Course?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try {
                        DBUtils.deletecourse(cho2.getValue());
                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The course is not deleted!");
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
                    try{
                        if(combo2.getValue()!="Information"){
                            DBUtils.changecourse(cho2.getValue(),(String) combo2.getValue(),tf_newinf.getText());
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
                    alert1.setContentText("The course is not Updated!");
                    alert1.showAndWait();
                }
            }
        });
        button_create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Create Course?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try{
                        DBUtils.createcourse( new Kurs (tf_name.getText(),Integer.parseInt(tf_number.getText()),Integer.parseInt(tf_fee.getText()),cho1.getValue()));

                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The course is not created!");
                    alert1.showAndWait();
                }
            }
        });
        button_regstudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Register student?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try {
                        DBUtils.registerstudent(cho3.getValue(),cho4.getValue());
                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The student is not registered!");
                    alert1.showAndWait();
                }
            }
        });
        button_regteacher.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Register teacher?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try {
                        DBUtils.registerteacher(cho5.getValue(),cho6.getValue());
                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The teacher is not registered!");
                    alert1.showAndWait();
                }
            }
        });
        button_unregt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Unregister teacher?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try {
                        DBUtils.unregisterteacher(cho5.getValue(),cho6.getValue());
                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The teacher is not unregistered!");
                    alert1.showAndWait();
                }
            }
        });
        button_unregs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Unregister student?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try {
                        DBUtils.unregisterstudent(cho3.getValue(),cho4.getValue());
                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The student is not unregistered!");
                    alert1.showAndWait();
                }
            }
        });

        ObservableList<String> list2 = FXCollections.observableArrayList("Course Name","Course Number","Course Fee");
        combo2.setItems(list2);

        listk= DBUtils.getdatalehrer();
        for(Lehrer l: listk){
            cho1.getItems().add(l.getIDnummer());
        }
        for(Kurs k: listm){
            cho2.getItems().add(k.getKursnummer());
        }
        lista= DBUtils.getdatastudent();
        for(Studieren s: lista){
            cho3.getItems().add(s.getIDnummer());
        }
        for(Kurs k: listm) {
            cho4.getItems().add(k.getKursnummer());
        }
         for(Lehrer l: listk) {
             cho5.getItems().add(l.getIDnummer());
         }
        for(Kurs k: listm) {
            cho6.getItems().add(k.getKursnummer());
        }

    }
    public void setUserInformation(String username){
        label_welcome.setText("Welcome \n"+ username+"!");
    }
}



