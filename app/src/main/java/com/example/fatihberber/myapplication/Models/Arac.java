package com.example.fatihberber.myapplication.Models;

public class Arac {

   private String XKoordinat;
   private String YKoordinat;
   private Integer MarkaId;
   private Integer ModelId;
   private Number Ucret;
   private Integer KM;
   private String Yakit;
   private String Kasa;
   private String Yil;
   private String Vites;
   private String Aciklama;
   private Integer Goruntulenme;
   private Integer OrtalamaPuan;
   private String AracDurum ;

public Arac(String xkoordinat,
        String ykoordinat,
        Integer markaId,
        Integer modelId,
        Number ucret,
        Integer km,
        String yakit,
        String kasa,
        String yil,
        String vites,
        String aciklama,
        Integer goruntulenme,
        Integer ortalamapuan,
        String aracdurum){


    XKoordinat=xkoordinat;
    YKoordinat= ykoordinat;
    MarkaId= markaId;
    ModelId= modelId;
    Ucret= ucret;
    KM=km;
    Yakit= yakit;
    Kasa= kasa;
    Yil= yil;
    Vites= vites;
    Aciklama= aciklama;
    Goruntulenme= goruntulenme;
    OrtalamaPuan= ortalamapuan;
    AracDurum = aracdurum;



}


    public String getXKoordinat() {
        return XKoordinat;
    }

    public String getYKoordinat() {
        return YKoordinat;
    }

    public Integer getMarkaId() {
        return MarkaId;
    }

    public Integer getModelId() {
        return ModelId;
    }

    public Number getUcret() {
        return Ucret;
    }

    public Integer getKM() {
        return KM;
    }

    public String getYakit() {
        return Yakit;
    }

    public String getKasa() {
        return Kasa;
    }

    public String getYil() {
        return Yil;
    }

    public String getVites() {
        return Vites;
    }

    public String getAciklama() {
        return Aciklama;
    }

    public Integer getGoruntulenme() {
        return Goruntulenme;
    }

    public Integer getOrtalamaPuan() {
        return OrtalamaPuan;
    }

    public String getAracDurum() {
        return AracDurum;
    }
}
