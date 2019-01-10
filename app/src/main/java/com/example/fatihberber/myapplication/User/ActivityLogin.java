package com.example.fatihberber.myapplication.User;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fatihberber.myapplication.Fragment.KiralikArac;
import com.example.fatihberber.myapplication.Fragment.MyCar;
import com.example.fatihberber.myapplication.MapsActivity;
import com.example.fatihberber.myapplication.Models.UyeBilgi;
import com.example.fatihberber.myapplication.Process.Session;
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
    EditText parola;
    EditText email;
    TextView kayit;
    Integer usersid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        init();
    }

    public void init(){
        button = findViewById(R.id.btn_girisyap);
        parola = findViewById(R.id.parola);
        email = findViewById(R.id.eposta);
        kayit = findViewById(R.id.kayit);

        kayit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                kayit();
            }

        });
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkEditTexts();
                getUyebilgi();
            }
        });



    }

    public void kayit(){

        Intent i = new Intent(ActivityLogin.this,ActivityKayit.class);
        startActivity(i);

    }
    public boolean checkEditTexts() {
        if (email.getText().toString().matches("") || parola.getText().toString().matches("")) {
            if (email.length() == 0)
                email.setError("Email can not null");

            if (parola.length() == 0) {
                parola.setError("Password length must be between 6 - 20");
            }
            return false;
        } else {
            if (email.length() == 0) {
                email.setError("Email can not null");
                return false;
            } else if (parola.length() == 0 || parola.length() < 6 || parola.length() > 20) {
                parola.setError("Password length must be between 6 - 20");
                return false;
            } else {
                return true;
            }
        }
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
                    String e_mail = m.getEMail();
                    String password_ = m.getSifre();
                    if (!e_mail.matches("") && !password_.matches("") && e_mail != null && password_ != null) {
                        if (e_mail.equals(email.getText().toString()) && password_.equals(parola.getText().toString())) {
                            usersid=m.getUserId();
                            entry = true;
                            break;
                        }
                    }
                }
                if (entry) {
                    Session session=new Session(getBaseContext());
                    session.setUserId(email.getText().toString());
                    session.setUsersId(usersid);





                    Intent i = new Intent(ActivityLogin.this,MapsActivity.class);
                    startActivity(i);
                } else {
                    email.setError("Check E mail");
                    parola.setError("Check Password");
                }






                Toast.makeText(getBaseContext(), "OKKK", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<UyeBilgi>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "onFailure " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
