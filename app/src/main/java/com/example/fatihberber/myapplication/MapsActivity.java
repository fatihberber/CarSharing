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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fatihberber.myapplication.Fragment.KiralikArac;
import com.example.fatihberber.myapplication.Fragment.MyCar;
import com.example.fatihberber.myapplication.Fragment.Profil;
import com.example.fatihberber.myapplication.Fragment.kirala;
import com.example.fatihberber.myapplication.Models.Arac;
import com.example.fatihberber.myapplication.Models.Lokasyon;
import com.example.fatihberber.myapplication.Models.UyeBilgi;
import com.example.fatihberber.myapplication.Process.Session;
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

import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ImageButton Ibutton;
    ImageView imagebuton;
    TextView model, isim, ucret;
    View aracbilgi;
    ImageButton gizle;
    MaterialRatingBar ratingbar;
    String UserId;
    int user;
    int knt;


    static List<Lokasyon> lokasyonlar = new ArrayList<>();
    static List<UyeBilgi> uyeBilgis = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Session session=new Session(getBaseContext());
       knt= session.getknt();
if(knt!=12){
        Fragment selectedFragment = null;
        selectedFragment = new KiralikArac();
        getSupportFragmentManager().beginTransaction().replace(R.id.harita, selectedFragment).commit();
    }


        getArac();

        session.setAracId2(0);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Ibutton = findViewById(R.id.button5);
        aracbilgi = findViewById(R.id.aracbilgi);
        isim = findViewById(R.id.textView10);
        aracbilgi.setVisibility(View.GONE);
        ratingbar = findViewById(R.id.ratingbar1);
        ucret = findViewById(R.id.textView12);
        model = findViewById(R.id.model);
        gizle = findViewById(R.id.gizle);
        imagebuton=findViewById(R.id.imageView2);
        imagebuton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ///buraya araç kiralanacak ekran gelecek
        aracbilgi.setVisibility(View.GONE);
        Fragment selectedFragment = null;
        selectedFragment = new kirala();
        getSupportFragmentManager().beginTransaction().replace(R.id.harita, selectedFragment).commit();


    }
});



        gizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aracbilgi.setVisibility(View.GONE);
            //    Toast.makeText(getBaseContext(), "OKKK", Toast.LENGTH_LONG).show();
            }
        });
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

            String value = extras.getString("send_string");


        } catch (Exception e) {

            Intent i2 = new Intent(getApplicationContext(), haritayenileme.class);
            startActivity(i2);
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
                    aracbilgi.setVisibility(View.GONE);
                    selectedActivity = new MapsActivity();

                    break;
                case R.id.araba:
                    aracbilgi.setVisibility(View.GONE);
                    selectedFragment = new MyCar();
                    break;
                case R.id.profil:
                    aracbilgi.setVisibility(View.GONE);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng elazig = new LatLng(38.674253, 39.221514);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(elazig, 14f));
        mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String yaz = marker.getTitle();
                String durum = "1 ";
                for (Lokasyon l : lokasyonlar) {
                    String knt = Integer.toString(l.getAracId());
                    String knt2 = Integer.toString(l.getUserId());
                    if (yaz.equals(knt)) {
                        for (UyeBilgi n : uyeBilgis) {
                            String knt3 = Integer.toString(n.getUserId());
                            if (knt2.equals(knt3)) {
                                isim.setText(n.getAdi() + " " + n.getSoyadi());
                                if (l.getAracDurum().matches("Dolu")) {
                                    durum = "gösterme";
                                }
                                else{
                                    Session session=new Session(getBaseContext());
                                    session.setAracId2(l.getAracId());


                                }
                                break;
                            }
                        }
                        ucret.setText(Float.toString(l.getUcret()) + " TL");
                        ratingbar.setRating(l.getOrtalamaPuan());
                        model.setText("Saatlik Kiralama Ücreti");
                    }
                }
                if (durum.matches("gösterme")) {

                    aracbilgi.setVisibility(View.GONE);
                } else {
                    aracbilgi.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
        for (Lokasyon l : lokasyonlar) {

            double x = Double.parseDouble(l.getLokasyonx());
            double y = Double.parseDouble(l.getLokasyony());
            LatLng ev2 = new LatLng(x, y);
            String title = Integer.toString(l.getAracId());
            String durum = l.getAracDurum();

            drawmaker(title, ev2, durum);


        }
    }

    public void drawmaker(String title, LatLng latLng, String durum) {
        MarkerOptions marker = new MarkerOptions();
        if (durum.matches("Dolu")) {
            marker.position(latLng).title(title).icon(BitmapDescriptorFactory.fromResource(R.drawable.carpng));
            mMap.addMarker(marker);
        } else {
            marker.position(latLng).title(title).icon(BitmapDescriptorFactory.fromResource(R.drawable.carpng2));
            mMap.addMarker(marker);

        }

    }


    //lokasyonların alındığı kısım
    public void getArac() {
        final Session session=new Session(getBaseContext());
        user=session.getUsersId();
        session.setAracId(0);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        WebAPI api = retrofit.create(WebAPI.class);
        Call<List<Arac>> call = api.getArac();
        Call<List<UyeBilgi>> call2 = api.getUyeler();


        call.enqueue(new Callback<List<Arac>>() {
            @Override
            public void onResponse(Call<List<Arac>> call, Response<List<Arac>> response) {
                List<Arac> aracbilgileri = response.body();
                for (Arac m : aracbilgileri) {
                    if(m.getUserId().equals(user))
                    {
                        session.setAracId(m.getAracId());


                    }
                    lokasyonlar.add(new Lokasyon(m.getAracId(), m.getXKoordinat(), m.getYKoordinat(), m.getUserId(), m.getUcret(), m.getOrtalamaPuan(), m.getModelAd(), m.getAdi(), m.getSoyadi(), m.getAracDurum()));

                }


              //  Toast.makeText(getBaseContext(), "OKKK", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Arac>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "onFailure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        call2.enqueue(new Callback<List<UyeBilgi>>() {
            @Override
            public void onResponse(Call<List<UyeBilgi>> call2, Response<List<UyeBilgi>> response) {
                List<UyeBilgi> aracbilgileri = response.body();
                for (UyeBilgi m : aracbilgileri) {

                    uyeBilgis.add(new UyeBilgi(m.getUserId(), m.getAdi(), m.getSoyadi()));

                }


                // Toast.makeText(getBaseContext(), "OKKK", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<UyeBilgi>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "onFailure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}