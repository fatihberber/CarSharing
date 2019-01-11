package com.example.fatihberber.myapplication.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fatihberber.myapplication.Models.UyeBilgi;
import com.example.fatihberber.myapplication.Process.Session;
import com.example.fatihberber.myapplication.R;
import com.example.fatihberber.myapplication.Service.WebAPI;

import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profil extends Fragment {
    TextView ad;
    TextView soyad;
    TextView tc;
    TextView email;
    TextView tel;
    TextView dogum;
    String userId="1";
    int userIdd;

    public Profil() {
        // Required empty public constructor




    }

public static View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

view  = inflater.inflate(R.layout.fragment_profil, container, false);

        ad=view.findViewById(R.id.profilAd);
        soyad=view.findViewById(R.id.profilSoyad);
        tc=view.findViewById(R.id.profilTC);
        email=view.findViewById(R.id.profilEmail);
        tel=view.findViewById(R.id.profilTel);
        dogum=view.findViewById(R.id.profilTarih);
       Session session=new Session(getContext());
       userId=session.getUserId();
        getUyebilgi();
        // Inflate the layout for this fragment
        return view;
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

                        ad.setText("Ad : "+m.getAdi());
                        soyad.setText("Soyad : "+m.getSoyadi());
                        tc.setText("TC : "+m.getTC());
                        email.setText("E-Mail : "+m.getEMail());
                        tel.setText("Telefon : "+m.getTelefon());
//tarih string to date yapmak için kullandığım format tanımları
                        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy MM dd");

                        String date2=m.getDogumTarihi();

//tarih string to date yapmak için kullandığım try catch bloğu
                        try {
                            Date date=dateformat.parse(date2);
                            date2=dateformat2.format(date);
                            dogum.setText("Doğum Tarihi : "+date2);
                        }catch (ParseException e) {
                            dogum.setText(m.getDogumTarihi());
                            e.printStackTrace();
                        }

break;
                    }
                }
            }
            @Override
            public void onFailure(Call<List<UyeBilgi>> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



}
