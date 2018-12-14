package com.example.fatihberber.myapplication.Models;

public class Lokasyon {
    int aracId;
    String lokasyonx;
    String lokasyony;

    public String getLokasyonx() {
        return lokasyonx;
    }

    public String getLokasyony() {
        return lokasyony;
    }

    public int getAracId() {
        return aracId;
    }

    public Lokasyon(int aracId, String lokasyonx, String lokasyony) {
        this.aracId = aracId;
        this.lokasyonx = lokasyonx;
        this.lokasyony = lokasyony;
    }
}
