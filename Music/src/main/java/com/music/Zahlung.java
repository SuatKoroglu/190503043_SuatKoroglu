package com.music;

public class Zahlung {
    private Datum zahlungdatum;
    private Integer gesamtbetrag;
    private Integer studentnummer;
    private Integer kursnummer;

    public Zahlung(Datum zahlungdatum, Integer gesamtbetrag, Integer studentnummer, Integer kursnummer) {
        this.zahlungdatum = zahlungdatum;
        this.gesamtbetrag = gesamtbetrag;
        this.studentnummer = studentnummer;
        this.kursnummer = kursnummer;
    }

    public Zahlung() {
    }

    public Datum getZahlungdatum() {
        return zahlungdatum;
    }

    public Integer getGesamtbetrag() {
        return gesamtbetrag;
    }

    public void setZahlungdatum(Datum zahlungdatum) {
        this.zahlungdatum = zahlungdatum;
    }

    public void setGesamtbetrag(Integer gesamtbetrag) {
        this.gesamtbetrag = gesamtbetrag;
    }
}
