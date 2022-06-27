package com.music;

public class Studieren extends Person{
    private Integer studentennummer;
    private Integer angemeldeteKurs;
    private Integer gebühr;
    private Integer rechnungen;
    private String abwesenheitstermine;

    public Studieren(String name, String nachname, Integer telefonnummer, String adresse, String email, Integer IDnummer, Integer studentennummer, Integer angemeldeteKurs, Integer gebühr, Integer rechnungen, String abwesenheitstermine) {
        super(name, nachname, telefonnummer, adresse, email, IDnummer);
        this.studentennummer = studentennummer;
        this.angemeldeteKurs = angemeldeteKurs;
        this.gebühr = gebühr;
        this.rechnungen = rechnungen;
        this.abwesenheitstermine = abwesenheitstermine;
    }

    public Integer getStudentennummer() {
        return studentennummer;
    }

    public void setStudentennummer(Integer studentennummer) {
        this.studentennummer = studentennummer;
    }

    public Integer getAngemeldeteKurs() {
        return angemeldeteKurs;
    }

    public void setAngemeldeteKurs(Integer angemeldeteKurs) {
        this.angemeldeteKurs = angemeldeteKurs;
    }

    public Integer getGebühr() {
        return gebühr;
    }

    public void setGebühr(Integer gebühr) {
        this.gebühr = gebühr;
    }

    public Integer getRechnungen() {
        return rechnungen;
    }

    public void setRechnungen(Integer rechnungen) {
        this.rechnungen = rechnungen;
    }

    public String getAbwesenheitstermine() {
        return abwesenheitstermine;
    }

    public void setAbwesenheitstermine(String abwesenheitstermine) {
        this.abwesenheitstermine = abwesenheitstermine;
    }

    public Studieren() {}
}
