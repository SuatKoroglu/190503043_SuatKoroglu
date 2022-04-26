module com.music {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;


    opens com.music to javafx.fxml;
    exports com.music;
}