package com.example.fatihberber.myapplication.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fatihberber.myapplication.MapsActivity;
import com.example.fatihberber.myapplication.Models.UyeBilgi;
import com.example.fatihberber.myapplication.Models.Yolculuk;
import com.example.fatihberber.myapplication.Process.Session;
import com.example.fatihberber.myapplication.R;
import com.example.fatihberber.myapplication.Service.WebAPI;

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
public class KiralikArac extends Fragment {


    public KiralikArac() {
        // Required empty public constructor
    }
    TextView karac1,karac2,karac3,karac4,karac5,karac6;
    int user;
    boolean entry=false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_kiralik_arac, container, false);


        karac1=view.findViewById(R.id.karac1);
        karac2=view.findViewById(R.id.karac2);
        karac3=view.findViewById(R.id.karac3);

        Session session=new Session(getContext());
        user=session.getUsersId();


        doldur();






        return view;
    }
public void doldur(){

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(WebAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    WebAPI api = retrofit.create(WebAPI.class);
    Call<List<Yolculuk>> call = api.getYolculuk();

    call.enqueue(new Callback<List<Yolculuk>>() {
        @Override
        public void onResponse(Call<List<Yolculuk>> call, Response<List<Yolculuk>> response) {
            List<Yolculuk> yolculuks = response.body();
            boolean entry = false;
            for (Yolculuk m : yolculuks) {
                   if(m.getKiralayanId().equals(user)){
                    entry=true;
             karac1.setText("Kiralayan : "+m.getKiralayanId());
                  karac2.setText("Araç Sahip :"+m.getAracSahipId());
                  karac3.setText("Araç id : "+m.getAracId());
break;


              }


            }
            if(!entry){
                entry=true;
Session session=new Session(getContext());
session.setknt(12);
                Intent i = new Intent(getActivity(), MapsActivity.class);
                startActivity(i);
                Toast.makeText(getContext(), "maps e gidiyon" , Toast.LENGTH_LONG).show();


            }
        }
        @Override
        public void onFailure(Call<List<Yolculuk>> call, Throwable t) {
            Toast.makeText(getContext(), "onFailure" + t.getMessage(), Toast.LENGTH_LONG).show();
        }
    });


}





}





