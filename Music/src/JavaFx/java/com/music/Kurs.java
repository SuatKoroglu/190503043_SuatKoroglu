package com.music;

public class Kurs {
    private String kursname;
    private Integer kursnummer;
    private Integer betrag;
    private Lehrer kurslehrer;


    public Kurs(String kursname, Integer kursnummer, Integer betrag, Lehrer kurslehrer){
        this.kursname = kursname;
        this.kursnummer= kursnummer;
        this.betrag = betrag;
        this.kurslehrer= kurslehrer;
    }
    public Kurs(){}

    public String getKursname() {
        return kursname;
    }

    public Integer getKursnummer() {
        return kursnummer;
    }

    public Integer getBetrag() {
        return betrag;
    }

    public Lehrer getKurslehrer() {
        return kurslehrer;
    }

    public void setKursname(String kursname) {
        this.kursname = kursname;
    }

    public void setKursnummer(Integer kursnummer) {
        this.kursnummer = kursnummer;
    }

    public void setBetrag(Integer betrag) {
        this.betrag = betrag;
    }

    public void setKurslehrer(Lehrer kurslehrer) {
        this.kurslehrer = kurslehrer;
    }
}
