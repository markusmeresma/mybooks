package com.example.mybooks;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class CollectionsFragment extends Fragment {

    private static Collections collections;

    public CollectionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collections, container, false);

        Button btnFavouritesCollection = (Button) view.findViewById(R.id.btnFavouritesCollection);
        Button btnCurrentlyReadingCollection = (Button) view.findViewById(R.id.btnCurrentlyReading);
        Button btnWantToReadCollection = (Button) view.findViewById(R.id.btnWantToRead);
        Button btnReadCollection = (Button) view.findViewById(R.id.btnRead);

        // Set on click listeners to open collections
        btnFavouritesCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Access favourites list from collections here and do stuff with recyclerview
            }
        });

        btnCurrentlyReadingCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnWantToReadCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnReadCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}