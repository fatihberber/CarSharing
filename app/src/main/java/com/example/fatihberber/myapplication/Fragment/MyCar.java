package com.example.fatihberber.myapplication.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fatihberber.myapplication.Models.Arac;
import com.example.fatihberber.myapplication.Models.Lokasyon;
import com.example.fatihberber.myapplication.Models.Marka;
import com.example.fatihberber.myapplication.Models.Model;
import com.example.fatihberber.myapplication.Models.UyeBilgi;
import com.example.fatihberber.myapplication.Process.Session;
import com.example.fatihberber.myapplication.R;
import com.example.fatihberber.myapplication.Service.RetrofitClient;
import com.example.fatihberber.myapplication.Service.WebAPI;
import com.example.fatihberber.myapplication.User.ActivityKayit;
import com.example.fatihberber.myapplication.User.ActivityLogin;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCar extends Fragment implements AdapterView.OnItemSelectedListener {

    Button kayit;
    int user=0;
    TextView koordinantx;
    TextView koordinanty;
    TextView ucret2;
    TextView aciklama2;




   TextView Ucret;
   TextView  KM;
   TextView Plaka;
   TextView Aciklama;
    Spinner YakitTipi;
    Spinner VitesTipi;
    Spinner Marka;
    Spinner Modelspinner;
    public List<Marka> markalar = new ArrayList<>();
    public List<Model> Modeller = new ArrayList<>();
    public List<Arac> Arac = new ArrayList<>();
    List<String> yakittipi = new ArrayList<String>();
    List<String> vitestipi = new ArrayList<String>();
    Integer Markaid;
    Integer Modelid;
    boolean arcknt=true;
    FrameLayout mycar;
    FrameLayout varsa;
    int userId;

    public static View view;

    public MyCar() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_car, container, false);
//arackontrol();
mycar=view.findViewById(R.id.mycarframelayout);
varsa=view.findViewById(R.id.varsabilgi);
//arackontrol();
        try {
            Session session=new Session(getContext());
            user=session.getAracId();
        }catch (Exception e){

        }
     if(user!=0){
mycar.setVisibility(View.GONE);
varsa.setVisibility(View.VISIBLE);

         koordinantx=view.findViewById(R.id.Koordinatx);
         koordinanty=view.findViewById(R.id.Koordinanty);
         ucret2=view.findViewById(R.id.Ucret);
         aciklama2=view.findViewById(R.id.aciklama2);




         arackontrol();







     }
     else {
         mycar.setVisibility(View.VISIBLE);
         varsa.setVisibility(View.GONE);
         kayit = view.findViewById(R.id.AracKayit);
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
         Marka = view.findViewById(R.id.MarkaSpinner);
         Modelspinner = view.findViewById(R.id.ModelSpinner);

         Marka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> arg0, View arg1,
                                        int arg2, long arg3) {

                 for (Marka m : markalar) {
                     if (m.getAracMarka().equals(Marka.getSelectedItem())) {
                         Markaid = m.getMarkaId();
                         break;
                     }

                 }
                 getAracmodel();
             }

             @Override
             public void onNothingSelected(AdapterView<?> arg0) {
             }
         });

         Modelspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> arg0, View arg1,
                                        int arg2, long arg3) {

                 for (Model m : Modeller) {
                     if (m.getModelAd().equals(Modelspinner.getSelectedItem())) {
                         Modelid = m.getModelId();
                         break;
                     }

                 }

             }

             @Override
             public void onNothingSelected(AdapterView<?> arg0) {
             }
         });
         Ucret = view.findViewById(R.id.SaatlikUcret);
         KM = view.findViewById(R.id.AracKM);
         Plaka = view.findViewById(R.id.plaka);
         Aciklama = view.findViewById(R.id.Aciklama);


         // Inflate the layout for this fragment
         kayit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 arackaydet();
             }

         });
     }
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
               // Toast.makeText(getContext(), "OKKK", Toast.LENGTH_LONG).show();
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
        Session session=new Session(getContext());
        Integer userId=session.getUsersId();
        String ucret=Ucret.getText().toString();
        String km=KM.getText().toString();
        String yakit=YakitTipi.getSelectedItem().toString();
        String plaka=Plaka.getText().toString();
        String vites=VitesTipi.getSelectedItem().toString();
        String aciklama=Aciklama.getText().toString();
        Boolean knt=false;
        knt=kontrol(plaka);
        if(knt.equals(false)){
        Call<Arac> call = RetrofitClient
                .getmInstance()
                .getApi()
               .Arackayit(userId,"38.681774","39.205707",Markaid ,Modelid,Float.valueOf(ucret),Integer.parseInt(km),yakit,plaka,"2018",vites,aciklama,"Boş");
               // .Arackayit(12 ,12,Float.valueOf(12),12,"asa","a","2018","a","aa");
//
        call.enqueue(new Callback<Arac>() {
            @Override
            public void onResponse(Call<Arac> call, Response<Arac> response) {
                try {

                  //  Toast.makeText(getContext(), "Kaydedildi", Toast.LENGTH_LONG).show();


                } catch (Exception e) {

                    Toast.makeText(getContext(), "onResponse da patladı", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Arac> call, Throwable t) {
                Toast.makeText(getContext(), "Onfailure da patladı", Toast.LENGTH_LONG).show();}

        });
    }




    }

    public Boolean kontrol(final String plaka){

        final boolean[] entry = {false};

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        WebAPI api = retrofit.create(WebAPI.class);
        Call<List<Arac>> call = api.getArac();



        call.enqueue(new Callback<List<Arac>>() {
            @Override
            public void onResponse(Call<List<Arac>> call, Response<List<Arac>> response) {
                Arac = response.body();
                for(Arac m : Arac){
                    if(m.getKasa().equals(plaka)){
                        entry[0] = true;
break;
                    }



                }
            }

            @Override
            public void onFailure(Call<List<Arac>> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
return entry[0];
    }

    public void arackontrol(){

        Session session=new Session(getContext());
        userId=session.getUsersId();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        WebAPI api = retrofit.create(WebAPI.class);
        Call<List<Arac>> call2 = api.getArac();



        call2.enqueue(new Callback<List<Arac>>() {
            @Override
            public void onResponse(Call<List<Arac>> call, Response<List<Arac>> response) {
                Arac = response.body();
                for(Arac m : Arac){

                    if(m.getUserId().equals(userId)){
                        koordinantx.setText("Koordinant X :"+m.getXKoordinat().toString());
                        koordinanty.setText("Koordinant Y :"+m.getYKoordinat().toString());
                        ucret2.setText("Saatlik Ücret :"+m.getUcret().toString());
                        aciklama2.setText("Açıklama :"+m.getAciklama().toString());
break;

                    }
                }
            }
            @Override
            public void onFailure(Call<List<Arac>> call, Throwable t) {

                Toast.makeText(getContext(), "onFailure" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });





    }






}
