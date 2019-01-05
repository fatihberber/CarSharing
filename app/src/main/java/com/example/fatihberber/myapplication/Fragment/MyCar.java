package com.example.fatihberber.myapplication.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fatihberber.myapplication.Models.Arac;
import com.example.fatihberber.myapplication.Models.Lokasyon;
import com.example.fatihberber.myapplication.Models.Marka;
import com.example.fatihberber.myapplication.Models.Model;
import com.example.fatihberber.myapplication.Models.UyeBilgi;
import com.example.fatihberber.myapplication.R;
import com.example.fatihberber.myapplication.Service.WebAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCar extends Fragment implements AdapterView.OnItemSelectedListener {
    Integer MarkaId;
    Integer ModelId;
    Button kayit;
    Float Ucret;
    Integer KM;
    String Yakit;
    String Kasa;
    String Yil;
    String Vites;
    String Aciklama;
    Spinner YakitTipi;
    Spinner VitesTipi;
    Spinner Marka;
    Spinner Modelspinner;
    public List<Marka> markalar = new ArrayList<>();
    public List<Model> Modeller = new ArrayList<>();
    List<String> yakittipi = new ArrayList<String>();
    List<String> vitestipi = new ArrayList<String>();
    Integer Markaid;

    public static View view;

    public MyCar() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_car, container, false);
        kayit=view.findViewById(R.id.AracKayit);
        yakitekle();


//yakit tipi dolduruluyor
        YakitTipi = view.findViewById(R.id.YakitTipi);
        ArrayAdapter<String> adapterYakit;
        adapterYakit = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, yakittipi);
        adapterYakit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        YakitTipi.setAdapter(adapterYakit);
//vites tipi dolduruluyor
        VitesTipi = view.findViewById(R.id.VitesTipi);
        ArrayAdapter<String> adaptervites;
        adaptervites = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, vitestipi);
        adaptervites.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        VitesTipi.setAdapter(adaptervites);
        //Marka spinner dolduruluyor
        getArac();
        Marka=view.findViewById(R.id.MarkaSpinner);
        Modelspinner=view.findViewById(R.id.ModelSpinner);

        Marka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                for(Marka m:markalar){
                    if(m.getAracMarka().equals(Marka.getSelectedItem())){
                        Markaid=m.getMarkaId();
break;
                    }

                }
                getAracmodel();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });





        // Inflate the layout for this fragment
        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arackaydet();
            }

        });
        return view;
    }

    public void yakitekle() {

        yakittipi.add("Dizel");
        yakittipi.add("LPG");
        yakittipi.add("Benzin");
        yakittipi.add("Hybrid");
        yakittipi.add("Elektrikli");
        vitestipi.add("Otomatik");
        vitestipi.add("Manuel");

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }








    public void getArac() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        WebAPI api = retrofit.create(WebAPI.class);
        Call<List<Marka>> call = api.getMarka();



        call.enqueue(new Callback<List<Marka>>() {
            @Override
            public void onResponse(Call<List<Marka>> call, Response<List<Marka>> response) {
                markalar = response.body();
                List<String> markalar2=new ArrayList<>();
               for(com.example.fatihberber.myapplication.Models.Marka m : markalar){

                   markalar2.add(m.getAracMarka());


               }

                ArrayAdapter<String> adaptermarka;
                adaptermarka = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, markalar2);
                adaptermarka.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Marka.setAdapter(adaptermarka);
                Toast.makeText(getContext(), "OKKK", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Marka>> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });






    }

    public void getAracmodel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        WebAPI api = retrofit.create(WebAPI.class);
        Call<List<Model>> call = api.getModel();



        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                Modeller = response.body();
                List<String> modeller2=new ArrayList<>();
                for(Model m : Modeller){
if(m.getMarkaId().equals(Markaid))
                    modeller2.add(m.getModelAd());


                }

                ArrayAdapter<String> adaptermodel;
                adaptermodel = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, modeller2);
                adaptermodel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Modelspinner.setAdapter(adaptermodel);

            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });






    }




    public void arackaydet() {


String yaz=YakitTipi.getSelectedItem().toString();





    }










}
