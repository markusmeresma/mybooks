package com.example.mybooks;

import java.util.ArrayList;

public class Collections {

    private ArrayList<Book> favourites = new ArrayList<>();
    private ArrayList<Book> currentlyReading = new ArrayList();
    private ArrayList<Book> wantToRead = new ArrayList<>();
    private ArrayList<Book> read = new ArrayList<>();

    public void addToFavourites (Book book)
    {
        favourites.add(book);
    }

    public void addToCurrentlyReading (Book book)
    {
        currentlyReading.add(book);
    }

    public void addToWantToRead (Book book)
    {
        wantToRead.add(book);
    }

    public void addToRead (Book book)
    {
        read.add(book);
    }
}
