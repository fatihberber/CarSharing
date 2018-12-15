package com.example.fatihberber.myapplication.User;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fatihberber.myapplication.MapsActivity;
import com.example.fatihberber.myapplication.Models.UyeBilgi;
import com.example.fatihberber.myapplication.R;
import com.example.fatihberber.myapplication.Service.RetrofitClient;
import com.example.fatihberber.myapplication.Service.WebAPI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityKayit extends AppCompatActivity {
    private static final String TAG = "fatih";
    EditText ad;
    EditText soyad;
    TextView dogumtarih;
    EditText email;
    EditText parola, parola2;
    EditText tc;
    EditText telefon;
    Button kayit;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        ad = findViewById(R.id.adi);
        soyad = findViewById(R.id.soyadi);
        email = findViewById(R.id.eposta);
        parola = findViewById(R.id.parola);
        parola2 = findViewById(R.id.parola2);
        tc = findViewById(R.id.tc);
        telefon = findViewById(R.id.telefon);
        kayit = findViewById(R.id.btn_kayitol);
       dogumtarih=findViewById(R.id.dogumtarih);
       dogumtarih.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Calendar cal = Calendar.getInstance();
               int year = cal.get(Calendar.YEAR);
               int month = cal.get(Calendar.MONTH);
               int day = cal.get(Calendar.DAY_OF_MONTH);

               DatePickerDialog dialog = new DatePickerDialog(
                       ActivityKayit.this,
                       android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                       mDateSetListener,
                       year,month,day);
               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
               dialog.show();
           }
       });
mDateSetListener = new DatePickerDialog.OnDateSetListener(){
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        month = month + 1;
        Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "-" + day + "-" + year);

        String date = year + "-" + month + "-" + day;
        dogumtarih.setText(date);
    }
};


        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMembers();
            }

        });


    }


    //kayıt işmelerinin yapılacağı yer
    public void kayitislem(String ad,String soyad,
                           String email,
                           String parola,
                           String tc,String tel) {
        boolean kontrol;
        kontrol = checktext();

//validation işlemleri sorunsuz ise kayıt işlemine geçilecekUyeBilgi
        if (kontrol == true) {
            Date tarih = new Date();
            SimpleDateFormat bugun = new SimpleDateFormat("yyyy-MM-dd");
            String tarihstring = bugun.format(tarih);
            String dogtarih=dogumtarih.getText().toString();
            Call<UyeBilgi> call = RetrofitClient
                    .getmInstance()
                    .getApi()
                    .UyeBilgiKayit(ad ,soyad,dogtarih,"Hayır", email, parola, tc, tel,tarihstring);
//
            call.enqueue(new Callback<UyeBilgi>() {
                @Override
                public void onResponse(Call<UyeBilgi> call, Response<UyeBilgi> response) {
                    try {
                        Intent i = new Intent(ActivityKayit.this,ActivityLogin.class);
                        startActivity(i);

                    } catch (Exception e) {

                        Toast.makeText(getBaseContext(), "onResponse da patladı", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<UyeBilgi> call, Throwable t) {
                    Toast.makeText(getBaseContext(), t.getMessage() + " onFailure", Toast.LENGTH_LONG).show();
                }
            });


        } else {


        }


    }

    public boolean checktext() {
        if (ad.length() <= 2 || ad.length() >= 30) {
            ad.setError("Name length must be between 0 - 30");
        }
        if (soyad.length() <= 1 || soyad.length() >= 30)
            soyad.setError("Surname length must be between 0 - 30");

        if (email.length() <= 5)
            email.setError("Email can not null");

        if (parola.length() == 0) {
            parola.setError("Password length must be between 6 - 20");
            if (parola.getText().toString().matches(parola2.getText().toString())) {
                parola.setError("Password should be equals");
                parola.setError("Password should be equals");
            }
        }
        if (telefon.length() != 10) {
            telefon.setError("5** *** ** **");
            return false;
        } else {
            if (ad.length() == 0 || ad.length() >= 30) {
                ad.setError("Name length must be between 0 - 30");
                return false;
            } else if (soyad.length() == 0 || soyad.length() >= 30) {
                soyad.setError("Surname length must be between 0 - 30");
                return false;
            } else if (email.length() == 0) {
                email.setError("Email can not null");
                return false;
            } else if (parola.length() == 0) {
                parola.setError("Password length must be between 6 - 20");
                return false;
            } else if (!parola.getText().toString().matches(parola2.getText().toString())) {
                parola.setError("Password should be equals");
                parola2.setError("Password should be equals");
                return false;
            } else if (telefon.length() != 10) {
                telefon.setError("5** *** ** **");
                return false;
            } else {
                return true;
            }
        }
    }

    public void getMembers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        WebAPI api = retrofit.create(WebAPI.class);
        Call<List<UyeBilgi>> call = api.getUyeler();

        call.enqueue(new Callback<List<UyeBilgi>>() {
            @Override
            public void onResponse(Call<List<UyeBilgi>> call, Response<List<UyeBilgi>> response) {
                List<UyeBilgi> Uyeler = response.body();
                boolean entry = false;
                for (UyeBilgi m : Uyeler) {
                    String e_mail = m.getEMail();
                    if (!e_mail.matches("") && e_mail != null) {
                        if (e_mail.equals(email.getText().toString())) {
                            entry = true;
                            break;
                        }
                    }
                }
                if (entry) {
                    Toast.makeText(getBaseContext(), "This e mail allready registered.", Toast.LENGTH_LONG).show();
                } else {
                    kayitislem(ad.getText().toString(),
                            soyad.getText().toString(),
                            email.getText().toString(),
                            parola.getText().toString(),
                            tc.getText().toString(),telefon.getText().toString());
                }
            }


            @Override
            public void onFailure(Call<List<UyeBilgi>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "onFailure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }


}
