package com.example.fatihberber.myapplication;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.fatihberber.myapplication.Fragment.Profil;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomBarFragment extends Fragment {
    protected SearchView Searchbuton;

    public BottomBarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_bar, container, false);


        Searchbuton = view.findViewById(R.id.search_bar);
        Searchbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

       // getChildFragmentManager().beginTransaction().replace(R.id.bottom_bar, new Profil().commit();

        return view;
    }


}
