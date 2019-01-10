package com.example.fatihberber.myapplication.Models;

public class Yolculuk {
    public Integer getAracSahipId() {
        return AracSahipId;
    }

    public Integer getKiralayanId() {
        return KiralayanId;
    }

    public Integer getAracId() {
        return AracId;
    }

    Integer AracSahipId;
Integer KiralayanId;
Integer AracId;

public Yolculuk(Integer aracSahipId,
        Integer kiralayanId,
        Integer aracId){

    AracSahipId=aracSahipId;
    KiralayanId=kiralayanId;
    AracId=aracId;


}




}
