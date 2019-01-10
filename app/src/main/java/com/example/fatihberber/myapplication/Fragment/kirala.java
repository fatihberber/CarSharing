package com.example.fatihberber.myapplication.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fatihberber.myapplication.Models.Arac;
import com.example.fatihberber.myapplication.Models.Lokasyon;
import com.example.fatihberber.myapplication.Process.Session;
import com.example.fatihberber.myapplication.R;
import com.example.fatihberber.myapplication.Service.WebAPI;

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
    int aracid;

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
        Session session=new Session(getContext());
        aracid=session.getAracId2();
        doldur();
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

}
