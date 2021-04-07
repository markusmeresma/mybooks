package com.example.mybooks;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SearchFragment extends Fragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        final EditText searchInput = (EditText) view.findViewById(R.id.userInput);
        Button btnSearch = (Button) view.findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userQuery = searchInput.getText().toString();

                //Start new activity to find books
                Intent searchBookActivity = new Intent(getContext(), SearchBookActivity.class);
                searchBookActivity.putExtra("USER_QUERY", userQuery);
                startActivity(searchBookActivity);
            }
        });


        return view;
    }
}