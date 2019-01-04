package com.example.fatihberber.myapplication.Models;

public class Lokasyon {
    int aracId,UserId;
    String lokasyonx;
    String lokasyony;
    Float Ucret;
    int OrtalamaPuan;
    String adi;
    String soyadi;
    String modelAd;
    String aracdurum;

    public String getAdi() {
        return adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public String getModelAd() {
        return modelAd;
    }

    public String getLokasyonx() {
        return lokasyonx;
    }

    public String getLokasyony() {
        return lokasyony;
    }

    public int getAracId() {
        return aracId;
    }

    public int getUserId() {
        return UserId;
    }
    public String getAracDurum() {
        return aracdurum;
    }


    public Float getUcret() {
        return Ucret;
    }

    public int getOrtalamaPuan() {
        return OrtalamaPuan;
    }

    public Lokasyon(int aracId, String lokasyonx, String lokasyony,int UserId,Float Ucret,int OrtalamaPuan,String modelAd,String adi,String soyadi,String aracdurum) {
        this.aracId = aracId;
        this.lokasyonx = lokasyonx;
        this.lokasyony = lokasyony;
        this.UserId=UserId;
        this.OrtalamaPuan=OrtalamaPuan;
        this.Ucret=Ucret;
        this.modelAd=modelAd;
        this.adi=adi;
        this.soyadi=soyadi;
        this.aracdurum=aracdurum;
    }

    public Lokasyon() {
            }
}
