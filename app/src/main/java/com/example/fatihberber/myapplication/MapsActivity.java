package com.example.fatihberber.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fatihberber.myapplication.Fragment.MyCar;
import com.example.fatihberber.myapplication.Fragment.Profil;
import com.example.fatihberber.myapplication.Models.Arac;
import com.example.fatihberber.myapplication.Models.Lokasyon;
import com.example.fatihberber.myapplication.Service.WebAPI;
import com.example.fatihberber.myapplication.User.ActivityLogin;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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
    TextView model;
    View aracbilgi;
    ImageButton gizle;
    static List<Lokasyon> lokasyonlar = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getArac();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Ibutton = findViewById(R.id.button5);
        aracbilgi=findViewById(R.id.aracbilgi);
        aracbilgi.setVisibility(View.GONE);
        model=findViewById(R.id.model);
        gizle=findViewById(R.id.gizle);
        gizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aracbilgi.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), "OKKK", Toast.LENGTH_LONG).show();

            }});


        Ibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MapsActivity.this, ActivityLogin.class);
                startActivity(i);
            }
        });

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




        try {
            Bundle extras = getIntent().getExtras();
          String  value = extras.getString("send_string");
        }catch (Exception e){

            Intent i = new Intent(getApplicationContext(), haritayenileme.class);
            startActivity(i);
        }


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            Activity selectedActivity = null;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);

            switch (item.getItemId()) {
                case R.id.home:
                    selectedActivity = new MapsActivity();

                    break;
                case R.id.araba:
                    selectedFragment = new MyCar();
                    break;
                case R.id.profil:
                    //1 Toast.makeText(this,"Oldu",Toast.LENGTH_LONG).show();
                    selectedFragment = new Profil();
                    break;

            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.harita, selectedFragment).commit();
            }
            if (selectedActivity != null) {
                Intent i = new Intent(MapsActivity.this, MapsActivity.class);
                startActivity(i);
            }

            return true;
        }
    };

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
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Add a marker in Sydney and move the camera
        LatLng elazig = new LatLng(38.674253, 39.221514);
        mMap.addMarker(new MarkerOptions().position(elazig).title("Elazığ").icon(BitmapDescriptorFactory.fromResource(R.drawable.carpng)));
        LatLng ev = new LatLng(38.681371, 39.220385);
        mMap.addMarker(new MarkerOptions().position(ev).title("ev").icon(BitmapDescriptorFactory.fromResource(R.drawable.carpng)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(elazig, 14f));
        mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String yaz=marker.getTitle();
                aracbilgi.setVisibility(View.VISIBLE);
                model.setText(yaz);

                return false;
            }
        });

        for (Lokasyon l : lokasyonlar) {

            double x = Double.parseDouble(l.getLokasyonx());
            double y = Double.parseDouble(l.getLokasyony());
            LatLng ev2 = new LatLng(x, y);
            drawmaker(ev2);


        }
    }

    public void drawmaker(LatLng latLng) {
        MarkerOptions marker = new MarkerOptions();
        marker.position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.carpng));
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

                    lokasyonlar.add(new Lokasyon(m.getModelId(), m.getXKoordinat(), m.getYKoordinat()));

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
