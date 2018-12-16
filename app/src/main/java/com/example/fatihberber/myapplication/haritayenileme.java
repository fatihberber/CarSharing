package com.example.fatihberber.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class haritayenileme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haritayenileme);
        String s = "Deneme";
        Intent i = new Intent(haritayenileme.this, MapsActivity.class);
        i.putExtra("send_string",s);
        startActivity(i);


    }
}
