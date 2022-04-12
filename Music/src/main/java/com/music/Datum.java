package com.music;

public class Datum {
    private String jahre;
    private String monat;
    private String tag;

    public Datum(String jahre,String monat, String tag){
        this.jahre = jahre;
        this.monat = monat;
        this.tag = tag;
    }
    @Override
    public String toString()
    {
        return jahre+"/"+monat+"/"+tag;
    }
}
