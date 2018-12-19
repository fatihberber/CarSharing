package com.example.fatihberber.myapplication.Models;

import java.util.Date;

public class UyeBilgi {

    private Integer UserId;



    private String Role;
    private String Adi;
    private String Soyadi;
    private String DogumTarihi;
    private String Sigara;
    private String ResimYol;
    private String EMail;
    private String Sifre;
    private String TC;
    private String Telefon;
    private String UyeKayitTarihi;
    private String Deneyim;
    private int YapilanYolculukSayisi;
    private int MusteriPuan;


    public UyeBilgi(String role, String adi, String soyadi, String dogumTarihi, String sigara, String resimYol, String EMail, String sifre, String TC, String telefon, String uyeKayitTarihi, String deneyim, int yapilanYolculukSayisi, int musteriPuan) {

        Role = role;
        Adi = adi;
        Soyadi = soyadi;
        DogumTarihi = dogumTarihi;
        Sigara = sigara;
        ResimYol = resimYol;
        this.EMail = EMail;
        Sifre = sifre;
        this.TC = TC;
        Telefon = telefon;
        UyeKayitTarihi = uyeKayitTarihi;
        Deneyim = deneyim;
        YapilanYolculukSayisi = yapilanYolculukSayisi;
        MusteriPuan = musteriPuan;
    }
    public UyeBilgi( Integer userId,String adi, String soyadi) {
        UserId=userId;
        Adi = adi;
        Soyadi = soyadi;

    }


    public String getRole() {
        return Role;
    }

    public String getAdi() {
        return Adi;
    }

    public String getSoyadi() {
        return Soyadi;
    }
    public Integer getUserId() {
        return UserId;
    }

    public String getDogumTarihi() {
        return DogumTarihi;
    }

    public String getSigara() {
        return Sigara;
    }

    public String getResimYol() {
        return ResimYol;
    }

    public String getEMail() {
        return EMail;
    }

    public String getSifre() {
        return Sifre;
    }

    public String getTC() {
        return TC;
    }

    public String getTelefon() {
        return Telefon;
    }

    public String getUyeKayitTarihi() {
        return UyeKayitTarihi;
    }

    public String getDeneyim() {
        return Deneyim;
    }

    public int getYapilanYolculukSayisi() {
        return YapilanYolculukSayisi;
    }

    public int getMusteriPuan() {
        return MusteriPuan;
    }
}
