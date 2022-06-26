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
                int j= psInsert.executeUpdate();
                if (j==1){
                    Alert alert= new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Person Created!");
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
                int j= psInsert.executeUpdate();
                if (j==1){
                    Alert alert= new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Person Created!");
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
        if(info=="Phone number") info="telefonnummer";
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

                    String sql1 = "UPDATE lehrer SET " + info2 + "= " + "\'" + newinfo + "\'" + "  WHERE IDnummer=" + IDnummer;

                    String sql2 = "UPDATE student SET " + info1 + "= " + "\'" + newinfo + "\'" + " WHERE IDnummer=" + IDnummer;
                    preparedStatement = connection.prepareStatement(sql1);
                    int m = preparedStatement.executeUpdate();
                    preparedStatement = connection.prepareStatement(sql2);
                    int j = preparedStatement.executeUpdate();
                    if (m==1 ||j==1){
                        Alert alert= new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Updated!");
                        alert.show();
                    }
                }else {

                    String sql1 = "UPDATE lehrer SET " + info + "= " + "\'" + newinfo + "\'" + "  WHERE IDnummer=" + IDnummer;

                    String sql2 = "UPDATE student SET " + info + "= " + "\'" + newinfo + "\'" + " WHERE IDnummer=" + IDnummer;
                    preparedStatement = connection.prepareStatement(sql1);
                    int k = preparedStatement.executeUpdate();
                    preparedStatement = connection.prepareStatement(sql2);
                    int l = preparedStatement.executeUpdate();
                    if (k == 1 || l == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Deleted!");
                        alert.show();
                    }
                }

            }else{
                psDelete = connection.prepareStatement("DELETE from student WHERE IDnummer="+IDnummer);
                boolean j= psDelete.execute();
                if(!j){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
    public static void createcourse(String name, int coursenumber,int fee,int teacherid){
        Connection connection= null;
        PreparedStatement psInsert= null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet= null;
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM kurs WHERE kursnummer =?");
            psCheckUserExists.setInt(1, coursenumber);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("Course already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Course already exists!");
                alert.show();
            }else{
                connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
                psCheckUserExists = connection.prepareStatement("SELECT IDnummer FROM lehrer WHERE IDnummer=?");
                psCheckUserExists.setInt(1,teacherid);
                resultSet = psCheckUserExists.executeQuery();

                if (!resultSet.isBeforeFirst()){

                    System.out.println("Teacher not found in Database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provided credentials are incorrect!");
                    alert.show();
                }else {

                    psInsert = connection.prepareStatement("INSERT INTO kurs (kursname, kursnummer,betrag,kurslehrerid) VALUES (?,?,?,?)");
                    psInsert.setString(1, name);
                    psInsert.setInt(2, coursenumber);
                    psInsert.setInt(3, fee);
                    psInsert.setInt(4, teacherid);
                    int j= psInsert.executeUpdate();
                    if (j==1){
                        Alert alert= new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Course Created!");
                        alert.show();
                    }
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
    public static void changecourse( int coursenumber , String info, String newinfo){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String info1=null;
        String info2=null;
        if(info=="Course Name") info="kursname";
        if(info=="Course Number") info="kursnummer";
        if(info=="Course Fee") info="betrag";


        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            preparedStatement = connection.prepareStatement("SELECT kursnummer FROM kurs  WHERE kursnummer=? ");
            preparedStatement.setInt(1, coursenumber);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("Course not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else {

                String sql1 = "UPDATE kurs SET " + info + "= " + "\'" + newinfo + "\'" + "  WHERE kursnummer=" + coursenumber;
                preparedStatement = connection.prepareStatement(sql1);
                int k = preparedStatement.executeUpdate();

                if (k == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Updated!");
                    alert.show();
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
    public static void deletecourse(int Coursenumber){
        Connection connection= null;
        PreparedStatement psDelete= null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet= null;
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            psCheckUserExists = connection.prepareStatement("SELECT kursnummer FROM kurs WHERE kursnummer=?");
            psCheckUserExists.setInt(1,Coursenumber);
            resultSet = psCheckUserExists.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("Course not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else {
                psDelete = connection.prepareStatement("DELETE from kurs WHERE kursnummer=" + Coursenumber);
                boolean j = psDelete.execute();
                if (!j) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
    public static void registerstudent( int IDnummer , int coursenumber){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String info1=null;
        String info2=null;


        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            preparedStatement = connection.prepareStatement("SELECT kursnummer FROM kurs  WHERE kursnummer=? ");
            preparedStatement.setInt(1, coursenumber);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("Course not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else {
                connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
                preparedStatement = connection.prepareStatement("SELECT IDnummer FROM student  WHERE IDnummer=? ");
                preparedStatement.setInt(1, IDnummer);
                resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst() ){
                    System.out.println("Student not found in Database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provided credentials are incorrect!");
                    alert.show();

                }else {

                    String sql = "UPDATE student SET angemeldeteKurs= "  + "\'" + coursenumber + "\'" + " WHERE IDnummer=" + IDnummer;

                    preparedStatement = connection.prepareStatement(sql);
                    int l = preparedStatement.executeUpdate();
                    if (l == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Student is registered!");
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
    public static void unregisterstudent( int IDnummer , int coursenumber){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;



        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            preparedStatement = connection.prepareStatement("SELECT kursnummer FROM kurs  WHERE kursnummer=? ");
            preparedStatement.setInt(1, coursenumber);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("Course not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else {
                connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
                preparedStatement = connection.prepareStatement("SELECT IDnummer FROM student  WHERE IDnummer=? ");
                preparedStatement.setInt(1, IDnummer);
                resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst() ){
                    System.out.println("Student not found in Database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provided credentials are incorrect!");
                    alert.show();

                }else {

                    String sql = "UPDATE student SET angemeldeteKurs= "  + "\' 0"  + "\'" + " WHERE IDnummer=" + IDnummer;

                    preparedStatement = connection.prepareStatement(sql);
                    int l = preparedStatement.executeUpdate();
                    if (l == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Student is unregistered!");
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
    public static void registerteacher( int IDnummer , int coursenumber){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String info1=null;
        String info2=null;


        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            preparedStatement = connection.prepareStatement("SELECT kursnummer FROM kurs  WHERE kursnummer=? ");
            preparedStatement.setInt(1, coursenumber);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("Course not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else {
                connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
                preparedStatement = connection.prepareStatement("SELECT IDnummer FROM lehrer  WHERE IDnummer=? ");
                preparedStatement.setInt(1, IDnummer);
                resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst() ){
                    System.out.println("Teacher not found in Database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provided credentials are incorrect!");
                    alert.show();

                }else {

                    String sql = "UPDATE kurs SET kurslehrerid= "  + "\'" + IDnummer + "\'" + " WHERE kursnummer=" + coursenumber;

                    preparedStatement = connection.prepareStatement(sql);
                    int l = preparedStatement.executeUpdate();
                    if (l == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Teacher is registered!");
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
    public static void unregisterteacher( int IDnummer , int coursenumber){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;



        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            preparedStatement = connection.prepareStatement("SELECT kursnummer FROM kurs  WHERE kursnummer=? ");
            preparedStatement.setInt(1, coursenumber);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("Course not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else {
                connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
                preparedStatement = connection.prepareStatement("SELECT IDnummer FROM lehrer  WHERE IDnummer=? ");
                preparedStatement.setInt(1, IDnummer);
                resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst() ){
                    System.out.println("Teacher not found in Database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provided credentials are incorrect!");
                    alert.show();

                }else {
                    String sql = "UPDATE kurs SET kurslehrerid= "  + "\'0"  + "\'" + " WHERE kursnummer=" + coursenumber +" AND kurslehrerid="+IDnummer;

                    preparedStatement = connection.prepareStatement(sql);
                    int l = preparedStatement.executeUpdate();
                    if (l == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Teacher is unregistered!");
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
    public static void createbill(int billnumber,int totalamount,int studentid,int coursenumber,String dateofinvoice){
        Connection connection= null;
        PreparedStatement psInsert= null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet= null;
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM rechnung WHERE rechnungsnummer =?");
            psCheckUserExists.setInt(1, billnumber);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("Bill already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Bill already exists!");
                alert.show();
            }else{
                connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
                psCheckUserExists = connection.prepareStatement("SELECT IDnummer FROM student WHERE IDnummer=?");
                psCheckUserExists.setInt(1,studentid);
                resultSet = psCheckUserExists.executeQuery();

                if (!resultSet.isBeforeFirst()){

                    System.out.println("Student not found in Database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provided credentials are incorrect!");
                    alert.show();
                }else {
                    connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
                    psCheckUserExists = connection.prepareStatement("SELECT kursnummer FROM kurs WHERE kursnummer=?");
                    psCheckUserExists.setInt(1,coursenumber);
                    resultSet = psCheckUserExists.executeQuery();

                    if (!resultSet.isBeforeFirst()){

                        System.out.println("Course not found in Database");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                    }else {
                        psInsert = connection.prepareStatement("INSERT INTO rechnung (rechnungsnummer , gesamtbetrag, rechnungsdatum ,studentenID, kursnummer,Zahlungsstatus,zahlungsdatum) VALUES (?,?,?,?,?,?,?)");
                        psInsert.setInt(1, billnumber);
                        psInsert.setInt(2, totalamount);
                        psInsert.setString(3, dateofinvoice);
                        psInsert.setInt(4, studentid);
                        psInsert.setInt(5, coursenumber);
                        psInsert.setString(6, "");
                        psInsert.setString(7, "");
                        int j= psInsert.executeUpdate();
                        if (j==1){
                            Alert alert= new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Bill Created!");
                            alert.show();
                        }
                    }
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
    public static void changebill( int billnumber , String info, String newinfo){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String info1=null;
        String info2=null;
        if(info=="Bill Number") info="rechnungsnummer";
        if(info=="Total Amount") info="gesamtbetrag";
        if(info=="Date of invoice") info="rechnungsdatum";


        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            preparedStatement = connection.prepareStatement("SELECT rechnungsnummer FROM rechnung  WHERE rechnungsnummer=? ");
            preparedStatement.setInt(1, billnumber);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("Bill not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else {

                String sql1 = "UPDATE rechnung SET " + info + "= " + "\'" + newinfo + "\'" + "  WHERE rechnungsnummer=" + billnumber;
                preparedStatement = connection.prepareStatement(sql1);
                int k = preparedStatement.executeUpdate();

                if (k == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Updated!");
                    alert.show();
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
    public static void deletebill(int billnumber){
        Connection connection= null;
        PreparedStatement psDelete= null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet= null;
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            psCheckUserExists = connection.prepareStatement("SELECT rechnungsnummer FROM rechnung WHERE rechnungsnummer=?");
            psCheckUserExists.setInt(1,billnumber);
            resultSet = psCheckUserExists.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("Bill not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else {
                psDelete = connection.prepareStatement("DELETE from rechnung WHERE rechnungsnummer=" + billnumber);
                boolean j = psDelete.execute();
                if (!j) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
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


    public static void paybill( int IDnummer , String date){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;



        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root", "");
            preparedStatement = connection.prepareStatement("SELECT rechnungsnummer FROM rechnung WHERE rechnungsnummer=?");
            preparedStatement.setInt(1,IDnummer);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("Bill not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else {

                String sql = "UPDATE rechnung SET zahlungsdatum= "  + date + ", Zahlungsstatus=True WHERE rechnungsnummer=" + IDnummer;
                preparedStatement = connection.prepareStatement(sql);
                int l = preparedStatement.executeUpdate();
                if (l == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("The bill has been paid!");
                    alert.show();
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

}