package com.music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;

        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                HomePageController homePageController = loader.getController();
                homePageController.setUserInformation(username);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM user WHERE username =?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You can not use this username.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO user (username,password) VALUES (?,?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();

                changeScene(event, "homepage.fxml", "Welcome", username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password FROM user WHERE username=?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {

                System.out.println("User not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "homepage.fxml", "Welcome", username);
                    } else {
                        System.out.println("Password did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createstudent(Studieren studieren) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM student WHERE IDnummer =?");
            psCheckUserExists.setInt(1, studieren.getIDnummer());
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User already exists!");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO student (name, nachname, telefonnummer, adresse, email, IDnummer, studentennummer, angemeldeteKurs, gebühr, rechnungen, abwesenheitstermine) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                psInsert.setString(1, studieren.getName());
                psInsert.setString(2, studieren.getNachname());
                psInsert.setInt(3, studieren.getTelefonnummer());
                psInsert.setString(4, studieren.getAdresse());
                psInsert.setString(5, studieren.getEmail());
                psInsert.setInt(6, studieren.getIDnummer());
                psInsert.setInt(7, studieren.getStudentennummer());
                psInsert.setInt(8, studieren.getAngemeldeteKurs());
                psInsert.setInt(9, studieren.getGebühr());
                psInsert.setInt(10, studieren.getRechnungen());
                psInsert.setString(11, studieren.getAbwesenheitstermine());
                int j = psInsert.executeUpdate();
                if (j == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Person Created!");
                    alert.show();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createteacher(Lehrer lehrer) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM lehrer WHERE IDnummer =?");
            psCheckUserExists.setInt(1, lehrer.getIDnummer());
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User already exists!");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO lehrer (name, nachname, telefonnummer, adresse, email, IDnummer, gehalt, spezialisiertes_instrument,  personalnummer) VALUES (?,?,?,?,?,?,?,?,?)");
                psInsert.setString(1, lehrer.getName());
                psInsert.setString(2, lehrer.getNachname());
                psInsert.setInt(3, lehrer.getTelefonnummer());
                psInsert.setString(4, lehrer.getAdresse());
                psInsert.setString(5, lehrer.getEmail());
                psInsert.setInt(6, lehrer.getIDnummer());
                psInsert.setInt(7, lehrer.getGehalt());
                psInsert.setString(8, lehrer.getSpezialisiertes_instrument());
                psInsert.setInt(9, lehrer.getPersonalnummer());
                int j = psInsert.executeUpdate();
                if (j == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Person Created!");
                    alert.show();
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void changeperson(int IDnummer, String info, String newinfo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String info1 = null;
        String info2 = null;
        if (info == "Name") info = "name";
        if (info == "Surname") info = "nachname";
        if (info == "IDnumber") info = "IDnummer";
        if (info == "Phone number") info = "telefonnummer";
        if (info == "E-mail") info = "email";
        if (info == "Adress") info = "adresse";
        if (info == "Special instrument(ID)") info = "spezialisiertes_instrument";
        if (info == "Number") {
            info1 = "studentennummer";
            info2 = "personalnummer";
        }
        if (info == "Preis") {
            info1 = "gebühr";
            info2 = "gehalt";
        }
        if (info=="Special instrumentID(Teacher)") {
            info = "spezialisiertes_instrument";
        }
        if (info=="Absence(Student)"){
            info= "abwesenheitstermine";

        }


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            String sql3 = "SELECT lehrer.IDnummer, student.IDnummer FROM lehrer,student  WHERE lehrer.IDnummer= " + IDnummer + " OR student.IDnummer= " + IDnummer;
            preparedStatement = connection.prepareStatement(sql3);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User not found in Database!");
                alert.show();
            } else {

                if (info == "Number" || info == "Preis" || info=="Special instrumentID(Teacher)/Absence(Student)") {

                    String sql1 = "UPDATE lehrer SET " + info2 + "= " + "\'" + newinfo + "\'" + "  WHERE IDnummer=" + IDnummer;

                    String sql2 = "UPDATE student SET " + info1 + "= " + "\'" + newinfo + "\'" + " WHERE IDnummer=" + IDnummer;
                    preparedStatement = connection.prepareStatement(sql1);
                    int m = preparedStatement.executeUpdate();
                    preparedStatement = connection.prepareStatement(sql2);
                    int j = preparedStatement.executeUpdate();
                    if (m == 1 || j == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Updated!");
                        alert.show();
                    }
                }else if (info=="spezialisiertes_instrument"){
                    connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
                    preparedStatement = connection.prepareStatement("SELECT instrumentID FROM instrument  WHERE instrumentID=? ");
                    preparedStatement.setString(1, newinfo);
                    resultSet = preparedStatement.executeQuery();

                    if (!resultSet.isBeforeFirst()) {
                        System.out.println("Instrument not found in Database");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Instrument not found in Database!");
                        alert.show();
                    } else {
                        String sql1 = "UPDATE lehrer SET " + info + "= " + "\'" + newinfo + "\'" + "  WHERE IDnummer=" + IDnummer;
                        preparedStatement = connection.prepareStatement(sql1);
                        int k = preparedStatement.executeUpdate();
                        if (k == 1) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Updated!");
                            alert.show();
                        }
                    }

                }else if (info=="abwesenheitstermine") {
                    String sql1 = "UPDATE student SET " + info + "= " + "\'" + newinfo + "\'" + "  WHERE IDnummer=" + IDnummer;
                    preparedStatement = connection.prepareStatement(sql1);
                    int k = preparedStatement.executeUpdate();
                    if (k == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteperson(int IDnummer) {
        Connection connection = null;
        PreparedStatement psDelete = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT IDnummer FROM student WHERE IDnummer=?");
            psCheckUserExists.setInt(1, IDnummer);
            resultSet = psCheckUserExists.executeQuery();


            if (!resultSet.isBeforeFirst()) {
                psCheckUserExists = connection.prepareStatement("SELECT IDnummer FROM lehrer WHERE IDnummer=?");
                psCheckUserExists.setInt(1, IDnummer);
                resultSet = psCheckUserExists.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("User not found in Database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("User not found in Database!");
                    alert.show();
                } else {
                    psDelete = connection.prepareStatement("DELETE from lehrer WHERE IDnummer=" + IDnummer);
                    boolean j = psDelete.execute();
                    if (!j) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Deleted!");
                        alert.show();
                    }
                }

            } else {
                psDelete = connection.prepareStatement("DELETE from student WHERE IDnummer=" + IDnummer);
                boolean j = psDelete.execute();
                if (!j) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Deleted!");
                    alert.show();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psDelete != null) {
                try {
                    psDelete.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createcourse(Kurs kurs) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM kurs WHERE kursnummer =?");
            psCheckUserExists.setInt(1, kurs.getKursnummer());
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("Course already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Course already exists!");
                alert.show();
            } else {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
                psCheckUserExists = connection.prepareStatement("SELECT IDnummer FROM lehrer WHERE IDnummer=?");
                psCheckUserExists.setInt(1, kurs.getKurslehrerid());
                resultSet = psCheckUserExists.executeQuery();

                if (!resultSet.isBeforeFirst()) {

                    System.out.println("Teacher not found in Database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Teacher not found in Database!");
                    alert.show();
                } else {

                    psInsert = connection.prepareStatement("INSERT INTO kurs (kursname, kursnummer,betrag,kurslehrerid) VALUES (?,?,?,?)");
                    psInsert.setString(1, kurs.getKursname());
                    psInsert.setInt(2, kurs.getKursnummer());
                    psInsert.setInt(3, kurs.getBetrag());
                    psInsert.setInt(4, kurs.getKurslehrerid());
                    int j = psInsert.executeUpdate();
                    if (j == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Course Created!");
                        alert.show();
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void changecourse(int coursenumber, String info, String newinfo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String info1 = null;
        String info2 = null;
        if (info == "Course Name") info = "kursname";
        if (info == "Course Number") info = "kursnummer";
        if (info == "Course Fee") info = "betrag";


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT kursnummer FROM kurs  WHERE kursnummer=? ");
            preparedStatement.setInt(1, coursenumber);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Course not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Course not found in Database!");
                alert.show();
            } else {

                String sql1 = "UPDATE kurs SET " + info + "= " + "\'" + newinfo + "\'" + "  WHERE kursnummer=" + coursenumber;
                preparedStatement = connection.prepareStatement(sql1);
                int k = preparedStatement.executeUpdate();

                if (k == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Updated!");
                    alert.show();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deletecourse(int Coursenumber) {
        Connection connection = null;
        PreparedStatement psDelete = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT kursnummer FROM kurs WHERE kursnummer=?");
            psCheckUserExists.setInt(1, Coursenumber);
            resultSet = psCheckUserExists.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Course not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Course not found in Database!");
                alert.show();
            } else {
                psDelete = connection.prepareStatement("DELETE from kurs WHERE kursnummer=" + Coursenumber);
                boolean j = psDelete.execute();
                if (!j) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Deleted!");
                    alert.show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psDelete != null) {
                try {
                    psDelete.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void registerstudent(int IDnummer, int coursenumber) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String info1 = null;
        String info2 = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT kursnummer FROM kurs  WHERE kursnummer=? ");
            preparedStatement.setInt(1, coursenumber);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Course not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Course not found in Database!");
                alert.show();
            } else {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
                preparedStatement = connection.prepareStatement("SELECT IDnummer FROM student  WHERE IDnummer=? ");
                preparedStatement.setInt(1, IDnummer);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("Student not found in Database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Student not found in Database!");
                    alert.show();

                } else {

                    String sql = "UPDATE student SET angemeldeteKurs= " + "\'" + coursenumber + "\'" + " WHERE IDnummer=" + IDnummer;

                    preparedStatement = connection.prepareStatement(sql);
                    int l = preparedStatement.executeUpdate();
                    if (l == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Student is registered!");
                        alert.show();
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void unregisterstudent(int IDnummer, int coursenumber) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT kursnummer FROM kurs  WHERE kursnummer=? ");
            preparedStatement.setInt(1, coursenumber);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Course not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Course not found in Database!");
                alert.show();
            } else {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
                preparedStatement = connection.prepareStatement("SELECT IDnummer FROM student  WHERE IDnummer=? ");
                preparedStatement.setInt(1, IDnummer);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("Student not found in Database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Student not found in Database!");
                    alert.show();

                } else {

                    String sql = "UPDATE student SET angemeldeteKurs= " + "\' 0" + "\'" + " WHERE IDnummer=" + IDnummer;

                    preparedStatement = connection.prepareStatement(sql);
                    int l = preparedStatement.executeUpdate();
                    if (l == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Student is unregistered!");
                        alert.show();
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void registerteacher(int IDnummer, int coursenumber) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String info1 = null;
        String info2 = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT kursnummer FROM kurs  WHERE kursnummer=? ");
            preparedStatement.setInt(1, coursenumber);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Course not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Course not found in Database!");
                alert.show();
            } else {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
                preparedStatement = connection.prepareStatement("SELECT IDnummer FROM lehrer  WHERE IDnummer=? ");
                preparedStatement.setInt(1, IDnummer);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("Teacher not found in Database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Teacher not found in Database!");
                    alert.show();

                } else {

                    String sql = "UPDATE kurs SET kurslehrerid= " + "\'" + IDnummer + "\'" + " WHERE kursnummer=" + coursenumber;

                    preparedStatement = connection.prepareStatement(sql);
                    int l = preparedStatement.executeUpdate();
                    if (l == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Teacher is registered!");
                        alert.show();
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void unregisterteacher(int IDnummer, int coursenumber) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT kursnummer FROM kurs  WHERE kursnummer=? ");
            preparedStatement.setInt(1, coursenumber);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Course not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Course not found in Database!");
                alert.show();
            } else {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
                preparedStatement = connection.prepareStatement("SELECT IDnummer FROM lehrer  WHERE IDnummer=? ");
                preparedStatement.setInt(1, IDnummer);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("Teacher not found in Database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Teacher not found in Database!");
                    alert.show();

                } else {
                    String sql = "UPDATE kurs SET kurslehrerid= " + "\'0" + "\'" + " WHERE kursnummer=" + coursenumber + " AND kurslehrerid=" + IDnummer;

                    preparedStatement = connection.prepareStatement(sql);
                    int l = preparedStatement.executeUpdate();
                    if (l == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Teacher is unregistered!");
                        alert.show();
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createbill(Rechnung rechnung) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM rechnung WHERE rechnungsnummer =?");
            psCheckUserExists.setInt(1, rechnung.getRechnungsnummer());
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("Bill already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Bill already exists!");
                alert.show();
            } else {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
                psCheckUserExists = connection.prepareStatement("SELECT IDnummer FROM student WHERE IDnummer=?");
                psCheckUserExists.setInt(1, rechnung.getStudentenID());
                resultSet = psCheckUserExists.executeQuery();

                if (!resultSet.isBeforeFirst()) {

                    System.out.println("Student not found in Database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Student not found in Database!");
                    alert.show();
                } else {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
                    psCheckUserExists = connection.prepareStatement("SELECT kursnummer FROM kurs WHERE kursnummer=?");
                    psCheckUserExists.setInt(1, rechnung.getKursnummer());
                    resultSet = psCheckUserExists.executeQuery();

                    if (!resultSet.isBeforeFirst()) {

                        System.out.println("Course not found in Database");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Course not found in Database!");
                        alert.show();
                    } else {
                        psInsert = connection.prepareStatement("INSERT INTO rechnung (rechnungsnummer , gesamtbetrag, rechnungsdatum ,studentenID, kursnummer,Zahlungsstatus,zahlungsdatum) VALUES (?,?,?,?,?,?,?)");
                        psInsert.setInt(1, rechnung.getRechnungsnummer());
                        psInsert.setInt(2, rechnung.getGesamtbetrag());
                        psInsert.setString(3, rechnung.getRechnungsdatum());
                        psInsert.setInt(4, rechnung.getStudentenID());
                        psInsert.setInt(5, rechnung.getKursnummer());
                        psInsert.setString(6, rechnung.getZahlungsstatus());
                        psInsert.setString(7, rechnung.getZahlungsdatum());
                        int j = psInsert.executeUpdate();

                        String sql1 = "UPDATE student SET rechnungen=" + rechnung.getRechnungsnummer() + "  WHERE IDnummer=" + rechnung.getStudentenID();
                        psCheckUserExists = connection.prepareStatement(sql1);
                        psCheckUserExists.executeUpdate();

                        if (j == 1) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Bill Created!");
                            alert.show();
                        }
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void changebill(int billnumber, String info, String newinfo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String info1 = null;
        String info2 = null;
        if (info == "Bill Number") info = "rechnungsnummer";
        if (info == "Total Amount") info = "gesamtbetrag";
        if (info == "Date of invoice") info = "rechnungsdatum";


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT rechnungsnummer FROM rechnung  WHERE rechnungsnummer=? ");
            preparedStatement.setInt(1, billnumber);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Bill not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Bill not found in Database\"!");
                alert.show();
            } else {
                try {
                    String sql1 = "UPDATE rechnung SET " + info + "= " + "\'" + newinfo + "\'" + "  WHERE rechnungsnummer=" + billnumber;
                    preparedStatement = connection.prepareStatement(sql1);
                    int k = preparedStatement.executeUpdate();
                    if (info == "rechnungsnummer"){

                        String sql2 = "UPDATE student SET rechnungen=" + newinfo + "  WHERE rechnungen=" + billnumber;
                        preparedStatement = connection.prepareStatement(sql2);
                        preparedStatement.executeUpdate();

                    }
                    if (k == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Updated!");
                        alert.show();
                    }
                }catch (Exception e){
                    System.out.println("Bill number already used");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Bill number already used!");
                    alert.show();
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deletebill(int billnumber) {
        Connection connection = null;
        PreparedStatement psDelete = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT rechnungsnummer FROM rechnung WHERE rechnungsnummer=?");
            psCheckUserExists.setInt(1, billnumber);
            resultSet = psCheckUserExists.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Bill not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Bill not found in Database!");
                alert.show();
            } else {
                psDelete = connection.prepareStatement("DELETE from rechnung WHERE rechnungsnummer=" + billnumber);
                boolean j = psDelete.execute();
                if (!j) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Deleted!");
                    alert.show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psDelete != null) {
                try {
                    psDelete.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void paybill(int IDnummer, String date) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT rechnungsnummer FROM rechnung WHERE rechnungsnummer=?");
            preparedStatement.setInt(1, IDnummer);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Bill not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Bill not found in Database!");
                alert.show();
            } else {

                String sql = "UPDATE rechnung SET zahlungsdatum= " +"\'" + date + "\'"+ ", Zahlungsstatus=True WHERE rechnungsnummer=" + IDnummer;
                preparedStatement = connection.prepareStatement(sql);
                int l = preparedStatement.executeUpdate();
                if (l == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("The bill has been paid!");
                    alert.show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createinstrument(Instrument instrument) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM instrument WHERE instrumentID= ?");
            psCheckUserExists.setInt(1, instrument.getInstrumentID());
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("Instrument already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Instrument already exists!");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO instrument (instrumentID , instrumentname, ausleichstatus ,id_des_ausleihers) VALUES (?,?,?,?)");
                psInsert.setInt(1, instrument.getInstrumentID());
                psInsert.setString(2, instrument.getInstrumentname());
                psInsert.setString(3, instrument.getAusleichstatus());
                psInsert.setInt(4, instrument.getId_des_ausleihers());
                int j = psInsert.executeUpdate();
                if (j == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Instrument Created!");
                    alert.show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void changeinstrument(int instrumentid, String info, String newinfo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String info1 = null;
        String info2 = null;
        if (info == "Instrument Name") info = "instrumentname";
        if (info == "Instrument ID") info = "instrumentID ";


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT instrumentID FROM instrument  WHERE instrumentID=? ");
            preparedStatement.setInt(1, instrumentid);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Instrument not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Instrument not found in Database!");
                alert.show();
            } else {

                String sql1 = "UPDATE instrument SET " + info + "= " + "\'" + newinfo + "\'" + "  WHERE instrumentID=" + instrumentid;
                preparedStatement = connection.prepareStatement(sql1);
                int k = preparedStatement.executeUpdate();

                if (k == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Updated!");
                    alert.show();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteinstrument(int instrumentid) {
        Connection connection = null;
        PreparedStatement psDelete = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT instrumentID FROM instrument  WHERE instrumentID=? ");
            psCheckUserExists.setInt(1, instrumentid);
            resultSet = psCheckUserExists.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Instrument not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Instrument not found in Database!");
                alert.show();
            } else {
                psDelete = connection.prepareStatement("DELETE from instrument WHERE instrumentID=" + instrumentid);
                boolean j = psDelete.execute();
                if (!j) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Deleted!");
                    alert.show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psDelete != null) {
                try {
                    psDelete.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void barrowinstrument(int instrumentid, int barrowerid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT instrumentID FROM instrument  WHERE instrumentID=? ");
            preparedStatement.setInt(1, instrumentid);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Instrument not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Instrument not found in Database!");
                alert.show();
            } else {

                connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
                preparedStatement = connection.prepareStatement("select 1 from ( select IDnummer as IDnummer from lehrer union all select IDnummer from student) a where IDnummer = ?");
                preparedStatement.setInt(1, barrowerid);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("Person not found in Database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Person not found in Database!");
                    alert.show();

                } else {
                    String sql = "UPDATE instrument SET \tid_des_ausleihers= " + barrowerid + ", ausleichstatus=True WHERE instrumentID =" + instrumentid;
                    preparedStatement = connection.prepareStatement(sql);
                    int l = preparedStatement.executeUpdate();
                    if (l == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("The Instrument has been barrowed!");
                        alert.show();
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void unbarrowinstrument(int instrumentid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT instrumentID FROM instrument  WHERE instrumentID=? ");
            preparedStatement.setInt(1, instrumentid);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Instrument not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Instrument not found in Database!");
                alert.show();
            } else {

                String sql = "UPDATE instrument SET id_des_ausleihers= 0  ,ausleichstatus=False WHERE instrumentID =" + instrumentid;
                preparedStatement = connection.prepareStatement(sql);
                int l = preparedStatement.executeUpdate();
                if (l == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("The Instrument has been unbarrowed!");
                    alert.show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ObservableList<Studieren> getdatastudent() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<Studieren> list = FXCollections.observableArrayList();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT * FROM student");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Studieren(resultSet.getString("name"), resultSet.getString("nachname"), Integer.parseInt(resultSet.getString("telefonnummer")), resultSet.getString("adresse"), resultSet.getString("email"), Integer.parseInt(resultSet.getString("IDnummer")), Integer.parseInt(resultSet.getString("studentennummer")), Integer.parseInt(resultSet.getString("angemeldeteKurs")), Integer.parseInt(resultSet.getString("gebühr")), Integer.parseInt(resultSet.getString("rechnungen")), resultSet.getString("abwesenheitstermine")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static ObservableList<Lehrer> getdatalehrer() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<Lehrer> list = FXCollections.observableArrayList();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT * FROM lehrer");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Lehrer(resultSet.getString("name"), resultSet.getString("nachname"), Integer.parseInt(resultSet.getString("telefonnummer")), resultSet.getString("adresse"), resultSet.getString("email"), Integer.parseInt(resultSet.getString("IDnummer")), Integer.parseInt(resultSet.getString("gehalt")), resultSet.getString("spezialisiertes_instrument"), Integer.parseInt(resultSet.getString("personalnummer"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static ObservableList<Kurs> getdatakurs() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<Kurs> list = FXCollections.observableArrayList();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT * FROM kurs");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Kurs(resultSet.getString("kursname"),  Integer.parseInt(resultSet.getString("kursnummer")),   Integer.parseInt(resultSet.getString("betrag")), Integer.parseInt(resultSet.getString("kurslehrerid")) ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static ObservableList<Rechnung> getdatarechnung() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<Rechnung> list = FXCollections.observableArrayList();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT * FROM rechnung");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Rechnung(Integer.parseInt(resultSet.getString("rechnungsnummer")), Integer.parseInt(resultSet.getString("gesamtbetrag")),resultSet.getString("rechnungsdatum"),Integer.parseInt(resultSet.getString("studentenID")),Integer.parseInt(resultSet.getString("kursnummer")),resultSet.getString("Zahlungsstatus"),resultSet.getString("zahlungsdatum")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static ObservableList<Instrument> getdatainstrument() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<Instrument> list = FXCollections.observableArrayList();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT * FROM instrument");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Instrument(Integer.parseInt(resultSet.getString("instrumentID")),resultSet.getString("instrumentname"),resultSet.getString("ausleichstatus"),Integer.parseInt(resultSet.getString("id_des_ausleihers"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static void changepassword( String username, String password, String newpassword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password FROM user WHERE username=?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {

                System.out.println("User not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {

                        String sql1 = "UPDATE user SET password='" +newpassword + "'  WHERE username= '" + username+"'";
                        System.out.println(sql1);
                        preparedStatement = connection.prepareStatement(sql1);
                        int m = preparedStatement.executeUpdate();

                        if (m == 1) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Updated!");
                            alert.show();
                        }
                    } else {
                        System.out.println("Password did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void changeusername( String username, String password, String newusername) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password FROM user WHERE username=?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {

                System.out.println("User not found in Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "");
                        preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username =?");
                        preparedStatement.setString(1, newusername);
                        resultSet = preparedStatement.executeQuery();

                        if (resultSet.isBeforeFirst()) {
                            System.out.println("User already exists!");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("You can not use this username.");
                            alert.show();
                        } else {
                            String sql1 = "UPDATE user SET username='" +newusername + "'  WHERE username= '" + username+"'";
                            System.out.println(sql1);
                            preparedStatement = connection.prepareStatement(sql1);
                            int m = preparedStatement.executeUpdate();

                            if (m == 1) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setContentText("Updated!");
                                alert.show();
                            }
                        }

                    } else {
                        System.out.println("Password did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
