package com.music;

public class Instrument {
    private String instrumentname;
    private String ausleichstatus;
    private String name_des_ausleihers;

    public Instrument(String instrumentname, String ausleichstatus, String name_des_ausleihers){
        this.instrumentname= instrumentname;
        this.ausleichstatus= ausleichstatus;
        this.name_des_ausleihers= name_des_ausleihers;
    }

    public Instrument() {}

    public String getintsrumentname() {
        return instrumentname;
    }

    public String getAusleichstatus() {
        return ausleichstatus;
    }

    public String getName_des_ausleihers() {
        return name_des_ausleihers;
    }



    public void setIntsrumentname(String instrumentname) {
        this.instrumentname = instrumentname;
    }

    public void setAusleichstatus(String ausleichstatus) {
        this.ausleichstatus = ausleichstatus;
    }

    public void setName_des_ausleihers(String name_des_ausleihers) {
        this.name_des_ausleihers = name_des_ausleihers;
    }

    @Override
    public String toString()
    {
        return instrumentname;
    }
}

