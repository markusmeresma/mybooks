package com.example.mybooks;

public class Book {
    private String id;
    private String title;
    private String subtitle;
    private String description;

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

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

    public void setSubtitle(String s) {
        this.subtitle = s;
    }

    public void setDescription(String d) {
        this.description = d;
    }
}
