package com.example.fatihberber.myapplication.User;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fatihberber.myapplication.Models.UyeBilgi;
import com.example.fatihberber.myapplication.R;
import com.example.fatihberber.myapplication.Service.WebAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ActivityLogin extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        getUyebilgi();
        init();
    }

    public void init(){
        button = findViewById(R.id.btn_girisyap);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUyebilgi();
            }
        });
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
                Toast.makeText(getBaseContext(), "OKKK", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<UyeBilgi>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "onFailure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
