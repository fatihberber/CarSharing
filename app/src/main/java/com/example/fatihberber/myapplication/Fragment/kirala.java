package com.example.fatihberber.myapplication.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import android.widget.Toast;

import com.example.fatihberber.myapplication.Models.Arac;
import com.example.fatihberber.myapplication.Models.Lokasyon;
import com.example.fatihberber.myapplication.Models.UyeBilgi;
import com.example.fatihberber.myapplication.Models.Yolculuk;
import com.example.fatihberber.myapplication.Process.Session;
import com.example.fatihberber.myapplication.R;
import com.example.fatihberber.myapplication.Service.RetrofitClient;
import com.example.fatihberber.myapplication.Service.WebAPI;
import com.example.fatihberber.myapplication.User.ActivityKayit;
import com.example.fatihberber.myapplication.User.ActivityLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class kirala extends Fragment {


    public kirala() {
        // Required empty public constructor
    }
    TextView arac,arac1,arac2,arac3,arac4,arac5,arac6;
    Button yolculuk;
    int aracid;
    int kullaniciid;
    int sahipid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_kirala, container, false);

        arac=view.findViewById(R.id.Arac);
        arac1=view.findViewById(R.id.arac1);
        arac2=view.findViewById(R.id.arac2);
        arac3=view.findViewById(R.id.arac3);
        arac4=view.findViewById(R.id.arac4);
        arac5=view.findViewById(R.id.arac5);
        yolculuk=view.findViewById(R.id.Kirala);
        Session session=new Session(getContext());
        aracid=session.getAracId2();
        kullaniciid=session.getUsersId();
        doldur();

        yolculuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Tıklandı", Toast.LENGTH_LONG).show();

                kirala();
            }

        });



        return view;
    }

    public void doldur(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        WebAPI api = retrofit.create(WebAPI.class);
        Call<List<Arac>> call = api.getArac();
        call.enqueue(new Callback<List<Arac>>() {
            @Override
            public void onResponse(Call<List<Arac>> call, Response<List<Arac>> response) {
                List<Arac> aracbilgileri = response.body();
                for (Arac m : aracbilgileri) {
                   if(m.getAracId().equals(aracid)){
                       arac.setText("Araç Saatlik ücret :"+m.getUcret());
                       arac1.setText("Araç ID :"+m.getAracId());
                       arac2.setText("Plaka "+m.getKasa());
                       arac3.setText("Yakıt :"+m.getYakit());
                       arac4.setText("KM :"+m.getKM());
                       arac5.setText("Açıklama :"+m.getAciklama());
                       sahipid=m.getUserId();

}



                }


                Toast.makeText(getContext(), "OKKK", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Arac>> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });





    }

    public void kirala(){

        Call<Yolculuk> call = RetrofitClient
                .getmInstance()
                .getApi()
                .postYolculuk(sahipid,kullaniciid,aracid);
//
        call.enqueue(new Callback<Yolculuk>() {
            @Override
            public void onResponse(Call<Yolculuk> call, Response<Yolculuk> response) {
                try {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(WebAPI.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


                    WebAPI api = retrofit.create(WebAPI.class);
                    Call<List<Arac>> call2 = api.getArac();



                    call2.enqueue(new Callback<List<Arac>>() {
                        @Override
                        public void onResponse(Call<List<Arac>> call, Response<List<Arac>> response) {
                            List<Arac> aracbilgileri = response.body();
                            for (Arac m : aracbilgileri) {
                                if(m.getAracId().equals(aracid))
                                {
                                    Integer userid;
                                    String xkoordinat;
                                    String ykoordinat;
                                    Integer markaId;
                                    Integer modelId;
                                    Float ucret;
                                    Integer km;
                                    String yakit;
                                    String kasa;
                                    String yil;
                                    String vites;
                                    String aciklama;
                                    String aracdurum;


                                    userid=m.getUserId();
                                    xkoordinat=m.getXKoordinat();
                                    ykoordinat=m.getYKoordinat();
                                    markaId=m.getMarkaId();
                                    modelId=m.getModelId();
                                    ucret=m.getUcret();
                                    km=m.getKM();
                                    yakit=m.getYakit();
                                    kasa=m.getKasa();
                                    yil=m.getYil();
                                    vites=m.getVites();
                                    aciklama=m.getAciklama();
                                    aracdurum="Dolu";
                                    Call<Arac> call2 = RetrofitClient
                                            .getmInstance()
                                            .getApi()
                                            .putarac(aracid,aracid,userid,xkoordinat,ykoordinat,markaId,modelId,ucret,km,yakit,kasa,yil,vites,aciklama,aracdurum);
                                    call2.enqueue(new Callback<Arac>() {
                                        @Override
                                        public void onResponse(Call<Arac> call, Response<Arac> response) {


                                        }

                                        @Override
                                        public void onFailure(Call<Arac> call, Throwable t) {
                                        }
                                    });



                                }

                            }

                            }

                        @Override
                        public void onFailure(Call<List<Arac>> call, Throwable t) {
                        }
                    });











                    Toast.makeText(getContext(), "okkk", Toast.LENGTH_LONG).show();
                    Fragment selectedFragment = null;
                    selectedFragment = new KiralikArac();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.harita, selectedFragment).commit();

                } catch (Exception e) {

                    Toast.makeText(getContext(), "onResponse da patladı", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Yolculuk> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage() + " onFailure", Toast.LENGTH_LONG).show();
            }
        });





    }







}