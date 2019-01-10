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

    public Integer getYolculukId() {
        return YolculukId;
    }

    Integer YolculukId;
Integer KiralayanId;
Integer AracId;

public Yolculuk(Integer yolculukId,Integer aracSahipId,
        Integer kiralayanId,
        Integer aracId){
    YolculukId=yolculukId;
    AracSahipId=aracSahipId;
    KiralayanId=kiralayanId;
    AracId=aracId;


}




}
