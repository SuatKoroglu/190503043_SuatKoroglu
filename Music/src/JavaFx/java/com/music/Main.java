package com.music;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Log in!");
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        Instrument i = new Instrument();
        i.setIntsrumentname("asd");
        Lehrer p = new Lehrer();
        Studieren k = new Studieren();
        k.setName("Mahmut");
        k.setIDnummer(456);
        p.setName("asd");
        p.setInstrumente_die_sie_unterrichten_kann(i);
        p.setIDnummer(123);
        System.out.println(p.getInstrumente_die_sie_unterrichten_kann());

        p.addTermine( new Datum("2022","04","10"));
        p.addTermine( new Datum("2022","04","11"));
        p.addTermine( new Datum("2022","04","12"));
        p.getTermine();



        k.addTermine( new Datum("2022","05","10"));
        k.addTermine( new Datum("2022","05","11"));
        k.addTermine( new Datum("2022","05","12"));
        k.getTermine();

        k.deletePerson();
        k.getTermine();
        p.deletePerson();

    }
}