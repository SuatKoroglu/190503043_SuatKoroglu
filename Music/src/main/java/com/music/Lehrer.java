package com.music;

import java.io.InterruptedIOException;

public class Lehrer extends Person {
    private Integer gehalt;
    private Instrument spezialisiertes_instrument;
    private Instrument Instrumente_die_sie_unterrichten_kann;
    private Integer personalnummer;


    public Lehrer(String name, String nachname, Integer telefonnummer, String adresse, String email, Integer IDnummer, Integer gehalt, Instrument spezialisiertes_instrument, Instrument instrumente_die_sie_unterrichten_kann, Integer personalnummer) {
        super(name, nachname, telefonnummer, adresse, email, IDnummer);
        this.gehalt = gehalt;
        this.spezialisiertes_instrument = spezialisiertes_instrument;
        this.Instrumente_die_sie_unterrichten_kann = instrumente_die_sie_unterrichten_kann;
        this.personalnummer = personalnummer;
    }


    public Lehrer() {

    }

    public Integer getGehalt() {
        return gehalt;
    }

    public Instrument getSpezialisiertes_instrument() {
        return spezialisiertes_instrument;
    }

    public Instrument getInstrumente_die_sie_unterrichten_kann() {
        return Instrumente_die_sie_unterrichten_kann;
    }

    public Integer getPersonalnummer() {
        return personalnummer;
    }

    public void setGehalt(Integer gehalt) {
        this.gehalt = gehalt;
    }

    public void setSpezialisiertes_instrument(Instrument spezialisiertes_instrument) {
        this.spezialisiertes_instrument = spezialisiertes_instrument;
    }

    public void setInstrumente_die_sie_unterrichten_kann(Instrument instrumente_die_sie_unterrichten_kann) {
        Instrumente_die_sie_unterrichten_kann = instrumente_die_sie_unterrichten_kann;
    }

    public void setPersonalnummer(Integer personalnummer) {
        this.personalnummer = personalnummer;
    }
}
