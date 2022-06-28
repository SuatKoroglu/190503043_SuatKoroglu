/*
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
    @FXML
    private TableColumn<Studieren, String> col_sabsence;

    @FXML
    private TableColumn<Studieren, String> col_sadress;

    @FXML
    private TableColumn<Studieren, Integer> col_sbill;

    @FXML
    private TableColumn<Studieren, Integer> col_scourse;

    @FXML
    private TableColumn<Studieren, Integer> col_sfee;

    @FXML
    private TableColumn<Studieren, Integer> col_sid;

    @FXML
    private TableColumn<Studieren, String> col_smail;

    @FXML
    private TableColumn<Studieren, String> col_sname;

    @FXML
    private TableColumn<Studieren, Integer> col_snumber;

    @FXML
    private TableColumn<Studieren, String> col_ssurname;

    @FXML
    private TableColumn<Studieren, Integer> col_stel;
    @FXML
    private TableView<Studieren> table_students;
    @FXML
    private TableColumn<Lehrer, String> col_tadress;

    @FXML
    private TableColumn<Lehrer, Integer>  col_tid;

    @FXML
    private TableColumn<Lehrer, String> col_tinstrument;

    @FXML
    private TableColumn<Lehrer, String> col_tmail;

    @FXML
    private TableColumn<Lehrer, String> col_tname;

    @FXML
    private TableColumn<Lehrer, Integer>  col_tpersnummer;

    @FXML
    private TableColumn<Lehrer, Integer> col_tsalary;

    @FXML
    private TableColumn<Lehrer, String> col_tsurname;

    @FXML
    private TableColumn<Lehrer, Integer> col_ttel;
    @FXML
    private TableView<Lehrer> table_teacher;


    ObservableList<Studieren> listm;
    ObservableList<Lehrer> listk;

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

        col_sname.setCellValueFactory(new PropertyValueFactory<Studieren,String>("name"));
        col_ssurname.setCellValueFactory(new PropertyValueFactory<Studieren,String>("nachname"));
        col_stel.setCellValueFactory(new PropertyValueFactory<Studieren,Integer>("telefonnummer"));
        col_sadress.setCellValueFactory(new PropertyValueFactory<Studieren,String>("adresse"));
        col_smail.setCellValueFactory(new PropertyValueFactory<Studieren,String>("email"));
        col_sid.setCellValueFactory(new PropertyValueFactory<Studieren,Integer>("IDnummer"));
        col_snumber.setCellValueFactory(new PropertyValueFactory<Studieren,Integer>("studentennummer"));
        col_scourse.setCellValueFactory(new PropertyValueFactory<Studieren,Integer>("angemeldeteKurs"));
        col_sfee.setCellValueFactory(new PropertyValueFactory<Studieren,Integer>("gebühr"));
        col_sbill.setCellValueFactory(new PropertyValueFactory<Studieren,Integer>("rechnungen"));
        col_sabsence.setCellValueFactory(new PropertyValueFactory<Studieren,String>("abwesenheitstermine"));

        listm = DBUtils.getdatastudent();
        table_students.setItems(listm);

        col_tname.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("name"));
        col_tsurname.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("nachname"));
        col_ttel.setCellValueFactory(new PropertyValueFactory<Lehrer,Integer>("telefonnummer"));
        col_tadress.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("adresse"));
        col_tmail.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("email"));
        col_tid.setCellValueFactory(new PropertyValueFactory<Lehrer,Integer>("IDnummer"));
        col_tpersnummer.setCellValueFactory(new PropertyValueFactory<Lehrer,Integer>("personalnummer"));
        col_tsalary.setCellValueFactory(new PropertyValueFactory<Lehrer,Integer>("gehalt"));
        col_tinstrument.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("spezialisiertes_instrument"));

        listk= DBUtils.getdatalehrer();
        table_teacher.setItems(listk);

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
                    try {
                        DBUtils.deleteperson(Integer.parseInt(tf_infid.getText()));
                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
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
                    try {
                        DBUtils.changeperson(Integer.parseInt(tf_infid.getText()),(String) combo2.getValue(),tf_newinf.getText());

                    }catch (Exception e){
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning!");
                        alert1.setContentText("Please make sure the information you entered is correct");
                        alert1.showAndWait();
                    }
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
        ObservableList<String> list2 = FXCollections.observableArrayList("Name","Surname","IDnumber","Phone number","E-mail","Adress","Number","Preis","Special instrumentID(Teacher)","Absence(Student)");
        combo2.setItems(list2);
    }
    public void setUserInformation(String username){
        label_welcome.setText("Welcome \n"+ username+"!");
    }






}


*/
