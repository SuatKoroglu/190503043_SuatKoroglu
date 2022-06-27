package com.music;

import java.io.InterruptedIOException;

public class Rechnung {
    private Integer rechnungsnummer;
    private Integer gesamtbetrag;
    private String rechnungsdatum;
    private Integer studentenID;
    private Integer kursnummer;
    private String Zahlungsstatus;
    private String zahlungsdatum;

    public Rechnung(Integer rechnungsnummer, Integer gesamtbetrag, String rechnungsdatum, Integer studentenID, Integer kursnummer, String Zahlungsstatus,String zahlungdatum ){
        this.rechnungsnummer= rechnungsnummer;
        this.gesamtbetrag= gesamtbetrag;
        this.rechnungsdatum= rechnungsdatum;
        this.studentenID = studentenID;
        this.kursnummer = kursnummer;
        this.Zahlungsstatus= Zahlungsstatus;
        this.zahlungsdatum=zahlungdatum;
    }

    public Rechnung() {
    }

    public Integer getRechnungsnummer() {
        return rechnungsnummer;
    }

    public void setRechnungsnummer(Integer rechnungsnummer) {
        this.rechnungsnummer = rechnungsnummer;
    }

    public Integer getGesamtbetrag() {
        return gesamtbetrag;
    }

    public void setGesamtbetrag(Integer gesamtbetrag) {
        this.gesamtbetrag = gesamtbetrag;
    }

    public String getRechnungsdatum() {
        return rechnungsdatum;
    }

    public void setRechnungsdatum(String rechnungsdatum) {
        this.rechnungsdatum = rechnungsdatum;
    }

    public Integer getStudentenID() {
        return studentenID;
    }

    public void setStudentenID(Integer studentenID) {
        this.studentenID = studentenID;
    }

    public Integer getKursnummer() {
        return kursnummer;
    }

    public void setKursnummer(Integer kursnummer) {
        this.kursnummer = kursnummer;
    }

    public String getZahlungsstatus() {
        return Zahlungsstatus;
    }

    public void setZahlungsstatus(String zahlungsstatus) {
        Zahlungsstatus = zahlungsstatus;
    }

    public String getZahlungsdatum() {
        return zahlungsdatum;
    }

    public void setZahlungsdatum(String zahlungsdatum) {
        this.zahlungsdatum = zahlungsdatum;
    }
}
