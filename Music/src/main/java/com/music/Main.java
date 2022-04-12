package com.music;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
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