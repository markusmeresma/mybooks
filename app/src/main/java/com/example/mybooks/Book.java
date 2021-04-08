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

    /**
     *
     * @return String
     */
    public String getId() {
        return this.id;
    }

    /**
     *
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     *
     * @return String
     */
    public String getAuthors() {return this.authors; }

    /**
     *
     * @return String
     */
    public String getSubtitle() {
        return this.subtitle;
    }

    /**
     *
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     *
     * @param i
     */
    public void setId(String i) {
        this.id = i;
    }

    /**
     *
     * @param t
     */
    public void setTitle(String t) {
        this.title = t;
    }

    /**
     *
     * @param a
     */
    public void setAuthors(String a) { this.authors = a; }

    /**
     *
     * @param s
     */
    public void setSubtitle(String s) {
        this.subtitle = s;
    }

    /**
     *
     * @param d
     */
    public void setDescription(String d) {
        this.description = d;
    }
}
