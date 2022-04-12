package com.music;

public class Studieren extends Person{
    private Integer studentnummer;
    private Kurs angemeldeteKurs;
    private Integer gebühr;
    private Rechnung rechnungen;
    private Datum abwesenheitstermine;

    public Studieren(String name, String nachname, Integer telefonnummer, String adresse, String email, Integer IDnummer, Integer studentnummer, Kurs angemeldeteKurs, Integer gebühr, Rechnung rechnungen, Datum abwesenheitstermine) {
        super(name, nachname, telefonnummer, adresse, email, IDnummer);
        this.studentnummer = studentnummer;
        this.angemeldeteKurs = angemeldeteKurs;
        this.gebühr = gebühr;
        this.rechnungen = rechnungen;
        this.abwesenheitstermine = abwesenheitstermine;
    }

    public Studieren() {}
}
