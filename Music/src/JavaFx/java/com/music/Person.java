package com.music;

import java.util.ArrayList;
import java.util.HashMap;

public class Person {

    private String name;
    private String nachname;
    private Integer telefonnummer;
    private String adresse;
    private String email;
    private Integer IDnummer;
    private HashMap<Integer, ArrayList<Datum>> a = new HashMap<Integer,ArrayList<Datum>>();
    private ArrayList<Datum> termines = new ArrayList<Datum>();

    public Person(String name, String nachname, Integer telefonnummer, String adresse, String email, Integer IDnummer) {
        this.name = name;
        this.nachname = nachname;
        this.telefonnummer = telefonnummer;
        this.adresse = adresse;
        this.email = email;
        this.IDnummer = IDnummer;
    }

    public Person() {

    }



    public void addTermine(Datum termine){
        termines.add(termine);
        a.put(IDnummer,termines);
    }
    public void getTermine(){
        System.out.println(a);
    }
    public void setName(String name){
        this.name = name;
    }


    public void setTelefonnummer(Integer telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIDnummer(Integer IDnummer) {
        this.IDnummer = IDnummer;
    }
    public String getName(){
        return name;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Integer getTelefonnummer() {
        return telefonnummer;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public Integer getIDnummer() {
        return IDnummer;
    }

    public void deletePerson(){
        name = null;
        nachname = null;
        telefonnummer =null;
        adresse=null;
        email=null;
        IDnummer=null;
        a.clear();
        System.out.println("Die Person wird gelöscht");
    }

}
