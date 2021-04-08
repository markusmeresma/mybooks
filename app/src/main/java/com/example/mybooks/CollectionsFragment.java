package com.example.mybooks;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class CollectionsFragment extends Fragment {

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
                selectCollection(getString(R.string.Favourites));
            }
        });

        btnCurrentlyReadingCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCollection(getString(R.string.Currently_Reading));
            }
        });

        btnWantToReadCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCollection(getString(R.string.Want_to_Read));
            }
        });

        btnReadCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCollection(getString(R.string.Read));
            }
        });

        return view;
    }

    public void selectCollection (String selectedCollection)
    {
        String collection = selectedCollection;
        Intent intent = new Intent(getContext(), CheckCollectionActivity.class);
        intent.putExtra("COLLECTION", collection);
        startActivity(intent);
    }
}