package com.music;

import java.io.InterruptedIOException;

public class Rechnung {
    private Integer rechnungsnummer;
    private Integer gesamtbetrag;
    private Datum rechnungsdatum;
    private String kundename;

    public Rechnung(Integer rechnungsnummer, Integer gesamtbetrag, Datum rechnungsdatum, String kundename){
        this.rechnungsnummer= rechnungsnummer;
        this.gesamtbetrag= gesamtbetrag;
        this.rechnungsdatum= rechnungsdatum;
        this.kundename= kundename;
    }

    public Rechnung() {
    }

    public Integer getRechnungsnummer() {
        return rechnungsnummer;
    }

    public Integer getGesamtbetrag() {
        return gesamtbetrag;
    }

    public Datum getRechnungsdatum() {
        return rechnungsdatum;
    }

    public String getKundename() {
        return kundename;
    }

    public void setRechnungsnummer(Integer rechnungsnummer) {
        this.rechnungsnummer = rechnungsnummer;
    }

    public void setGesamtbetrag(Integer gesamtbetrag) {
        this.gesamtbetrag = gesamtbetrag;
    }

    public void setRechnungsdatum(Datum rechnungsdatum) {
        this.rechnungsdatum = rechnungsdatum;
    }

    public void setKundename(String kundename) {
        this.kundename = kundename;
    }
}
