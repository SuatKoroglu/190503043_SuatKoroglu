package com.music;

import Connection.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
    @FXML
    public Label textLabel;
    public TextField textField;

    @FXML
    public void button (ActionEvent actionEvent) throws SQLException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection= connectionClass.getConnection();
        String sql= "Please enter your name('"+textField.getText()+"')";
        Statement statement= connection.createStatement();
        statement.executeUpdate(sql);

        sql= "Select * From User";
        ResultSet resultSet= statement.executeQuery(sql);
        while (resultSet.next()){
            textLabel.setText(resultSet.getString(1));
        }


    }
}
