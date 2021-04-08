package com.example.mybooks;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Book implements Serializable {
    private String id;
    private String title;
    private String authors;
    private String subtitle;
    private String description;

    public Book () {}

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthors() {return this.authors; }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(String i) {
        this.id = i;
    }

    public void setTitle(String t) {
        this.title = t;
    }

    public void setAuthors(String a) { this.authors = a; }

    public void setSubtitle(String s) {
        this.subtitle = s;
    }

    public void setDescription(String d) {
        this.description = d;
    }
}
