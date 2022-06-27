package com.music;

public class Kurs {
    private String kursname;
    private Integer kursnummer;
    private Integer betrag;
    private Integer  	kurslehrerid;


    public Kurs(String kursname, Integer kursnummer, Integer betrag, Integer 	kurslehrerid){
        this.kursname = kursname;
        this.kursnummer= kursnummer;
        this.betrag = betrag;
        this.kurslehrerid= 	kurslehrerid;
    }
    public Kurs(){}

    public String getKursname() {
        return kursname;
    }

    public void setKursname(String kursname) {
        this.kursname = kursname;
    }

    public Integer getKursnummer() {
        return kursnummer;
    }

    public void setKursnummer(Integer kursnummer) {
        this.kursnummer = kursnummer;
    }

    public Integer getBetrag() {
        return betrag;
    }

    public void setBetrag(Integer betrag) {
        this.betrag = betrag;
    }

    public Integer getKurslehrerid() {
        return kurslehrerid;
    }

    public void setKurslehrerid(Integer kurslehrerid) {
        this.kurslehrerid = kurslehrerid;
    }
}
