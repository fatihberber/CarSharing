package com.example.fatihberber.myapplication.Service;

import com.example.fatihberber.myapplication.Models.Arac;
import com.example.fatihberber.myapplication.Models.UyeBilgi;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WebAPI {

    String BASE_URL ="http://192.168.1.34:60746/api/";
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
    @GET("Arac")
    //listin içindeki model adı get arac çağırırken ki ismi istediğimizi koyuyoruz
    Call<List<Arac>> getArac();



}