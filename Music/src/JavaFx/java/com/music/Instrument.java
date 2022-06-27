package com.music;

public class Instrument {
    private String instrumentname;
    private String ausleichstatus;
    private Integer id_des_ausleihers;
    private Integer instrumentID;


    public Instrument(int instrumentID ,String instrumentname, String ausleichstatus, int id_des_ausleihers){
        this.instrumentname= instrumentname;
        this.ausleichstatus= ausleichstatus;
        this.id_des_ausleihers= id_des_ausleihers;
        this.instrumentID= instrumentID;
    }

    public Instrument() {}

    public String getInstrumentname() {
        return instrumentname;
    }

    public void setInstrumentname(String instrumentname) {
        this.instrumentname = instrumentname;
    }

    public String getAusleichstatus() {
        return ausleichstatus;
    }

    public void setAusleichstatus(String ausleichstatus) {
        this.ausleichstatus = ausleichstatus;
    }

    public Integer getId_des_ausleihers() {
        return id_des_ausleihers;
    }

    public void setId_des_ausleihers(Integer id_des_ausleihers) {
        this.id_des_ausleihers = id_des_ausleihers;
    }

    public Integer getInstrumentID() {
        return instrumentID;
    }

    public void setInstrumentID(Integer instrumentID) {
        this.instrumentID = instrumentID;
    }

    @Override
    public String toString()
    {
        return instrumentname;
    }
}

