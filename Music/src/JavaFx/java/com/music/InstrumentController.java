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

public class InstrumentController implements Initializable {
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
    private Button button_person;

    @FXML
    private ComboBox combo2;
    @FXML
    private Button button_create;
    @FXML
    private Button button_barrow;
    @FXML
    private Button button_delete;
    @FXML
    private Button button_change;
    @FXML
    private Button button_unbarrow;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_newinf;
    @FXML
    private TableColumn<Instrument, Integer> col_barrowerid;

    @FXML
    private TableColumn<Instrument, Integer> col_id;

    @FXML
    private TableColumn<Instrument, String> col_name;

    @FXML
    private TableColumn<Instrument, String>  col_status;

    @FXML
    private TableView<Instrument> table_instrument;
    @FXML
    private ChoiceBox<Integer> cho1;

    @FXML
    private ChoiceBox<Integer> cho2;

    @FXML
    private ChoiceBox<Integer> cho3;


    ObservableList<Instrument> listm;
    ObservableList<Studieren> lists;
    ObservableList<Lehrer> listl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        col_name.setCellValueFactory(new PropertyValueFactory<Instrument,String>("instrumentname"));
        col_id.setCellValueFactory(new PropertyValueFactory<Instrument,Integer>("instrumentID"));
        col_status.setCellValueFactory(new PropertyValueFactory<Instrument,String>("ausleichstatus"));
        col_barrowerid.setCellValueFactory(new PropertyValueFactory<Instrument,Integer>("id_des_ausleihers"));


        listm = DBUtils.getdatainstrument();
        table_instrument.setItems(listm);

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
        button_create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Create Instrument?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try{
                        DBUtils.createinstrument( new Instrument( Integer.parseInt(tf_id.getText()),tf_name.getText(),"",0));
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
        button_barrow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Save Informations?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try {
                        DBUtils.barrowinstrument(cho2.getValue(),cho3.getValue());
                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The Instrument has not been barrowed!");
                    alert1.showAndWait();
                }
            }
        });
        button_unbarrow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Save Informations?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try {
                        DBUtils.unbarrowinstrument(cho2.getValue());
                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The Instrument has not been unbarrowed!");
                    alert1.showAndWait();
                }
            }
        });

        button_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Delete Instrument?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try {
                        DBUtils.deleteinstrument(cho1.getValue());
                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Warning!");
                    alert1.setContentText("The Instrument is not deleted!");
                    alert1.showAndWait();
                }
            }
        });
        button_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Change Informations?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try{
                        if(combo2.getValue()!="Information"){
                                DBUtils.changeinstrument(cho1.getValue(),(String) combo2.getValue(),tf_newinf.getText());
                        }else{
                            Alert alert1 = new Alert(Alert.AlertType.WARNING);
                            alert1.setTitle("Please Select!");
                            alert1.setContentText("Please Select");
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
                    alert1.setContentText("The instrument is not Updated!");
                    alert1.showAndWait();
                }
            }
        });


        ObservableList<String> list2 = FXCollections.observableArrayList("Instrument Name","Instrument ID");
        combo2.setItems(list2);

        for(Instrument i: listm){
            cho1.getItems().add(i.getInstrumentID());
        }
        for(Instrument i: listm){
            cho2.getItems().add(i.getInstrumentID());
        }
        lists= DBUtils.getdatastudent();
        listl= DBUtils.getdatalehrer();
        for(Studieren s: lists){
            cho3.getItems().add(s.getIDnummer());
        }
        for(Lehrer l: listl){
            cho3.getItems().add(l.getIDnummer());
        }




    }

    public void setUserInformation(String username){
        label_welcome.setText("Welcome \n"+ username+"!");
    }
}



