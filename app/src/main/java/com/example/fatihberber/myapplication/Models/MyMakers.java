package com.example.fatihberber.myapplication.Models;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.util.ArrayList;
import java.util.List;

public class MyMakers implements ClusterItem {

    private final LatLng mPosition;


    public MyMakers(String lokasyonx, String lokasyony) {
        double x = Double.parseDouble(lokasyonx);
        double y = Double.parseDouble(lokasyony);
        mPosition = new LatLng(x, y);

    }


    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSnippet() {
        return null;
    }
}
