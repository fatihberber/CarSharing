package com.example.fatihberber.myapplication;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.fatihberber.myapplication.Models.Arac;
import com.example.fatihberber.myapplication.Models.Lokasyon;
import com.example.fatihberber.myapplication.Models.UyeBilgi;
import com.example.fatihberber.myapplication.Service.WebAPI;
import com.example.fatihberber.myapplication.User.ActivityLogin;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ImageButton Ibutton;
    String lokasyonx;
    String lokasyony;
   static List<Lokasyon> lokasyonlar=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getArac();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Ibutton =findViewById(R.id.button3);
        Ibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MapsActivity.this,ActivityLogin.class);
                startActivity(i);
            }
        });



    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng elazig = new LatLng(38.674253, 39.221514);
        mMap.addMarker(new MarkerOptions().position(elazig).title("Elazığ"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(elazig,14f ));
        LatLng ev = new LatLng(38.681371, 39.220385);
        mMap.addMarker(new MarkerOptions().position(ev).title("ev"));




        출처: http://mainia.tistory.com/1616 [녹두장군 - 상상을 현실로]
        for(Lokasyon l : lokasyonlar){

            double x =Double.parseDouble(l.getLokasyonx());
            double y =Double.parseDouble(l.getLokasyony());
            LatLng ev2 = new LatLng(x, y);
           drawmaker(ev2);




        }



    }

    public void drawmaker(LatLng latLng)
    {
        MarkerOptions marker=new MarkerOptions();
        marker.position(latLng);
        mMap.addMarker(marker);

    }

    //lokasyonların alındığı kısım
    public void getArac() {
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

                    lokasyonlar.add(new Lokasyon(m.getModelId(),m.getXKoordinat(),m.getYKoordinat()));

                }


                Toast.makeText(getBaseContext(), "OKKK", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Arac>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "onFailure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
