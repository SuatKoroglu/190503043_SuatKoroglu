package com.music;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username){
        Parent root = null;

        if (username !=null){
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                HomePageController homePageController = loader.getController();
                homePageController.setUserInformation(username);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root,1280,720));
        stage.show();
    }
    public static void signUpUser(ActionEvent event, String username, String password){
        Connection connection= null;
        PreparedStatement psInsert= null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet= null;
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM user WHERE username =?");
            psCheckUserExists.setString(1,username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You can not use this username.");
                alert.show();
            }else{
                psInsert = connection.prepareStatement("INSERT INTO user (username,password) VALUES (?,?)");
                psInsert.setString(1,username);
                psInsert.setString(2,password);
                psInsert.executeUpdate();

                changeScene(event,"homepage.fxml","Welcome",username);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet !=null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists !=null){
                try {
                    psCheckUserExists.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psInsert != null){
                try {
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection !=null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void logInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            preparedStatement = connection.prepareStatement("SELECT password FROM user WHERE username=?");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){

                System.out.println("User not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else {
                while (resultSet.next()){
                    String retrievedPassword =resultSet.getString("password");
                    if (retrievedPassword.equals(password)){
                        changeScene(event,"homepage.fxml","Welcome",username);
                    }else {
                        System.out.println("Password did not match!");
                        Alert alert= new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (resultSet !=null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (preparedStatement !=null){
                try {
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection !=null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void createstudent(String name,String surname, int id,int telefon,String mail, String adress,int studentnumber,int fee){
        Connection connection= null;
        PreparedStatement psInsert= null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet= null;
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM student WHERE IDnummer =?");
            psCheckUserExists.setInt(1, id);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User already exists!");
                alert.show();
            }else{
                psInsert = connection.prepareStatement("INSERT INTO student (name, nachname, telefonnummer, adresse, email, IDnummer, studentennummer, angemeldeteKurs, gebühr, rechnungen, abwesenheitstermine) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                psInsert.setString(1,name);
                psInsert.setString(2,surname);
                psInsert.setInt(3,telefon);
                psInsert.setString(4,adress);
                psInsert.setString(5,mail);
                psInsert.setInt(6,id);
                psInsert.setInt(7,studentnumber);
                psInsert.setString(8,"");
                psInsert.setInt(9,fee);
                psInsert.setString(10,"");
                psInsert.setString(11,"");
                psInsert.executeUpdate();


            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet !=null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists !=null){
                try {
                    psCheckUserExists.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psInsert != null){
                try {
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection !=null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void createteacher(String name,String surname, int id,int telefon,String mail, String adress,int personalnumber,int gehalt){
        Connection connection= null;
        PreparedStatement psInsert= null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet= null;
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM lehrer WHERE IDnummer =?");
            psCheckUserExists.setInt(1, id);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User already exists!");
                alert.show();
            }else{
                psInsert = connection.prepareStatement("INSERT INTO lehrer (name, nachname, telefonnummer, adresse, email, IDnummer, gehalt, spezialisiertes_instrument, Instrumente_die_sie_unterrichten_kann , personalnummer) VALUES (?,?,?,?,?,?,?,?,?,?)");
                psInsert.setString(1,name);
                psInsert.setString(2,surname);
                psInsert.setInt(3,telefon);
                psInsert.setString(4,adress);
                psInsert.setString(5,mail);
                psInsert.setInt(6,id);
                psInsert.setInt(7,gehalt);
                psInsert.setString(8,"");
                psInsert.setString(9,"");
                psInsert.setInt(10,personalnumber);
                psInsert.executeUpdate();


            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet !=null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists !=null){
                try {
                    psCheckUserExists.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psInsert != null){
                try {
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection !=null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void changeperson( int IDnummer , String info, String newinfo){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String info1=null;
        String info2=null;
        if(info=="Name") info="name";
        if(info=="Surname") info="nachname";
        if(info=="IDnumber") info="IDnummer";
        if(info=="Phone Number") info="telefonnummer";
        if(info=="E-mail") info="email";
        if(info=="Adress") info="adresse";
        if(info=="Number") {
            info1 ="studentennummer";
            info2 ="personalnummer";
        }
        if(info=="Preis") {
            info1 ="gebühr";
            info2 ="gehalt";
        }



        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            String sql3 = "SELECT lehrer.IDnummer, student.IDnummer FROM lehrer,student  WHERE lehrer.IDnummer= "+IDnummer +" OR student.IDnummer= "+IDnummer;
            preparedStatement = connection.prepareStatement(sql3);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("User not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else {

                if(info=="Number" || info =="Preis" ){
                    System.out.println("Sa as");
                    String sql1= "UPDATE lehrer SET " + info2 +"= "+"\'" + newinfo+ "\'" +" FROM lehrer WHERE IDnummer="+ IDnummer;
                    String sql2= "UPDATE student SET " + info1 +"= "+"\'" + newinfo+ "\'" +" FROM student WHERE IDnummer="+ IDnummer;
                    preparedStatement = connection.prepareStatement(sql1);
                    int m = preparedStatement.executeUpdate();
                    preparedStatement = connection.prepareStatement(sql2);
                    int j = preparedStatement.executeUpdate();
                    if (m==1 ||j==1){
                        Alert alert= new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Updated!");
                        alert.show();
                    }
                }else {
                    System.out.println("izzet");
                    String sql1 = "UPDATE lehrer SET " + info + "= " + "\'" + newinfo + "\'" + "  WHERE IDnummer=" + IDnummer;
                    System.out.println(sql1);
                    String sql2 = "UPDATE student SET " + info + "= " + "\'" + newinfo + "\'" + " WHERE IDnummer=" + IDnummer;
                    preparedStatement = connection.prepareStatement(sql1);
                    int k = preparedStatement.executeUpdate();
                    preparedStatement = connection.prepareStatement(sql2);
                    int l = preparedStatement.executeUpdate();
                    if (k == 1 || l == 1) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Updated!");
                        alert.show();
                    }
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (resultSet !=null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (preparedStatement !=null){
                try {
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection !=null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void deleteperson(int IDnummer){
        Connection connection= null;
        PreparedStatement psDelete= null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet= null;
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            psCheckUserExists = connection.prepareStatement("SELECT IDnummer FROM student WHERE IDnummer=?");
            psCheckUserExists.setInt(1,IDnummer);
            resultSet = psCheckUserExists.executeQuery();


            if (!resultSet.isBeforeFirst()){
                psCheckUserExists = connection.prepareStatement("SELECT IDnummer FROM lehrer WHERE IDnummer=?");
                psCheckUserExists.setInt(1,IDnummer);
                resultSet = psCheckUserExists.executeQuery();
                if (!resultSet.isBeforeFirst()){
                    System.out.println("User not found in Database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provided credentials are incorrect!");
                    alert.show();
                }else {
                    psDelete = connection.prepareStatement("DELETE from lehrer WHERE IDnummer="+IDnummer);
                    boolean j= psDelete.execute();
                    if(!j){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Deleted!");
                        alert.show();
                    }
                }

            }else{
                psDelete = connection.prepareStatement("DELETE from student WHERE IDnummer="+IDnummer);
                boolean j= psDelete.execute();
                if(!j){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Deleted!");
                    alert.show();
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet !=null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists !=null){
                try {
                    psCheckUserExists.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psDelete != null){
                try {
                    psDelete.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection !=null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }



}