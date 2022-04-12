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
}
