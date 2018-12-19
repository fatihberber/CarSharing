package com.example.fatihberber.myapplication.Models;

public class Arac {
    private Integer AracId;
    private Integer UserId;


   private String XKoordinat;
   private String Adi;
   private String Soyadi;



    private String ModelAd;



   private String YKoordinat;
   private Integer MarkaId;
   private Integer ModelId;



    private Float Ucret;
   private Integer KM;
   private String Yakit;
   private String Kasa;
   private String Yil;
   private String Vites;
   private String Aciklama;
   private Integer Goruntulenme;
   private Integer OrtalamaPuan;
   private String AracDurum ;

public Arac(
        Integer aracid,
        Integer userid,
        String xkoordinat,
        String ykoordinat,
        Integer markaId,
        Integer modelId,
        Float ucret,
        Integer km,
        String yakit,
        /*String adi,
        String soyadi,
        String modelAd,*/
        String kasa,
        String yil,
        String vites,
        String aciklama,
        Integer goruntulenme,
        Integer ortalamapuan,
        String aracdurum,
        UyeBilgi uyeBilgi){

    AracId=aracid;
    UserId=userid;
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
   /* Adi=adi;
    Soyadi=soyadi;
    ModelAd=modelAd;*/
  // UyeBilgi=uyeBilgi;



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

    public Float getUcret() {
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
    public Integer getAracId() {
        return AracId;
    }

    public Integer getUserId() {
        return UserId;
    }

    public String getAdi() {
        return Adi;
    }

    public String getSoyadi() {
        return Soyadi;
    }

    public String getModelAd() {
        return ModelAd;
    }
}
