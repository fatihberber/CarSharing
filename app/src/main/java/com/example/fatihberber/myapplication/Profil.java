package com.example.fatihberber.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fatihberber.myapplication.Models.UyeBilgi;
import com.example.fatihberber.myapplication.Process.Session;
import com.example.fatihberber.myapplication.Service.WebAPI;
import com.example.fatihberber.myapplication.User.ActivityLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profil extends AppCompatActivity {
    TextView ad;
    TextView soyad;
    TextView tc;
    TextView email;
    TextView tel;
    TextView dogum;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ad=findViewById(R.id.profilAd);
        soyad=findViewById(R.id.profilSoyad);
        tc=findViewById(R.id.profilTC);
        email=findViewById(R.id.profilEmail);
        tel=findViewById(R.id.profilTel);
        dogum=findViewById(R.id.profilTarih);
        Session session=new Session(getBaseContext());
        userId=session.getUserId();
        getUyebilgi();
    }





    public void getUyebilgi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        WebAPI api = retrofit.create(WebAPI.class);
        Call<List<UyeBilgi>> call = api.getUyeler();

        call.enqueue(new Callback<List<UyeBilgi>>() {
            @Override
            public void onResponse(Call<List<UyeBilgi>> call, Response<List<UyeBilgi>> response) {
                List<UyeBilgi> uyebilgileri = response.body();
                boolean entry = false;
                for (UyeBilgi m : uyebilgileri) {

                    if(m.getEMail().equals(userId)){

                        ad.setText(m.getAdi());
                        soyad.setText(m.getSoyadi());
                        tc.setText(m.getTC());
                        email.setText(m.getEMail());
                        tel.setText(m.getTelefon());
                        dogum.setText(m.getDogumTarihi());
                    }
                }
            }
            @Override
            public void onFailure(Call<List<UyeBilgi>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "onFailure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }








}
