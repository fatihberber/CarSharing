package com.example.fatihberber.myapplication.Service;

import com.example.fatihberber.myapplication.Models.Arac;
import com.example.fatihberber.myapplication.Models.Marka;
import com.example.fatihberber.myapplication.Models.Model;
import com.example.fatihberber.myapplication.Models.UyeBilgi;
import com.example.fatihberber.myapplication.Models.Yolculuk;

import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WebAPI {

    String BASE_URL ="http://192.168.1.38:60746/api/";
/*
    //@Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("members")
    Call<ResponseBody> members(
            @Field("email") String email,
            @Field("memberPassword") String memberPassword,
            @Field("memberName") String memberName,
            @Field("surname") String surname,
            @Field("birthDate") String birthDate,
            @Field("gender") String gender,
            @Field("userTypeId") String userTypeId,
            @Field("TcIdentityNumber") String TcIdentityNumber,
            @Field("securityQuestion") String securityQuestion,
            @Field("securityAnswer") String securityAnswer,
            @Field("registrationDate") String registrationDate
    );
*/
    @GET("UyeBilgi")
    Call<List<UyeBilgi>> getUyeler();
    @GET("Marka")
    Call<List<Marka>> getMarka();
    @GET("Arac")
    //listin içindeki model adı get arac çağırırken ki ismi istediğimizi koyuyoruz
    Call<List<Arac>> getArac();
    @GET("Model")
        //listin içindeki model adı get arac çağırırken ki ismi istediğimizi koyuyoruz
    Call<List<Model>> getModel();
    @GET("Yolculuk")
    Call<List<Yolculuk>> getYolculuk();

    @FormUrlEncoded
    @POST("Yolculuk")
    Call<Yolculuk> postYolculuk(

            @Field("AracSahipId")  Integer AracSahipId,
            @Field("KiralayanId")  Integer KiralayanId,
            @Field("AracId")  Integer AracId

    );




    @DELETE("Yolculuk/{id}")
    Call<Void> DeleteYolculuk(@Path("id") int aracid);

    @FormUrlEncoded
    @PUT("Arac/{id}")
    Call<Arac> putarac(@Path("id") int aracid,
                       @Field("AracId")Integer aracidd,
                       @Field("UserId")Integer UserId,
                       @Field("XKoordinat")String XKoordinat,
                       @Field("YKoordinat")String YKoordinat,
                       @Field("MarkaId")Integer MarkaId,
                       @Field("ModelId")Integer ModelId,
                       @Field("Ucret")Float Ucret,
                       @Field("KM")Integer KM,
                       @Field("Yakit")String Yakit,
                       @Field("Kasa")String Kasa,
                       @Field("Yil")String Yil,
                       @Field("Vites")String Vites,
                       @Field("Aciklama")String Aciklama,
                       @Field("AracDurum")String AracDurum

    );


    @FormUrlEncoded
    @POST("UyeBilgi")
    Call<UyeBilgi> UyeBilgiKayit(

            @Field("Adi")  String Adi,
            @Field("Soyadi")  String Soyadi,
            @Field("DogumTarihi")  String dogumtarih,
            @Field("Sigara")  String sigara,
            @Field("EMail")  String EMail,
            @Field("Sifre")  String Sifre,
            @Field("TC")  String TC,
            @Field("Telefon")  String Telefon,
            @Field("UyeKayitTarihi") String uyetarih
    );
    @FormUrlEncoded
    @POST("Arac")
    Call<Arac> Arackayit(
            @Field("UserId")Integer UserId,
            @Field("XKoordinat")String XKoordinat,
            @Field("YKoordinat")String YKoordinat,
            @Field("MarkaId")Integer MarkaId,
            @Field("ModelId")Integer ModelId,
            @Field("Ucret")Float Ucret,
            @Field("KM")Integer KM,
            @Field("Yakit")String Yakit,
            @Field("Kasa")String Kasa,
            @Field("Yil")String Yil,
            @Field("Vites")String Vites,
            @Field("Aciklama")String Aciklama,
            @Field("AracDurum")String AracDurum

    );




}
