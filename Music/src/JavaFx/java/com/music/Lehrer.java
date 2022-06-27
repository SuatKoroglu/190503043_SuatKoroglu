package com.music;

import java.io.InterruptedIOException;

public class Lehrer extends Person {
    private Integer gehalt;
    private String spezialisiertes_instrument;
    private Integer personalnummer;


    public Lehrer(String name, String nachname, Integer telefonnummer, String adresse, String email, Integer IDnummer, Integer gehalt, String spezialisiertes_instrument,  Integer personalnummer) {
        super(name, nachname, telefonnummer, adresse, email, IDnummer);
        this.gehalt = gehalt;
        this.spezialisiertes_instrument = spezialisiertes_instrument;

        this.personalnummer = personalnummer;
    }


    public Lehrer() {

    }

    public Integer getGehalt() {
        return gehalt;
    }

    public String getSpezialisiertes_instrument() {
        return spezialisiertes_instrument;
    }



    public Integer getPersonalnummer() {
        return personalnummer;
    }

    public void setGehalt(Integer gehalt) {
        this.gehalt = gehalt;
    }

    public void setSpezialisiertes_instrument(String spezialisiertes_instrument) {
        this.spezialisiertes_instrument = spezialisiertes_instrument;
    }


    public void setPersonalnummer(Integer personalnummer) {
        this.personalnummer = personalnummer;
    }
}
