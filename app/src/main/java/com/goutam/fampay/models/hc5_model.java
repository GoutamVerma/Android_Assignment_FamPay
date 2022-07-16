package com.goutam.fampay.models;

public class hc5_model {
    private String title;
    private String description;
    private String url;
    private String image;

    // Constructor
    public hc5_model( String url, String image) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.image = image;
    }

    // Getter and Setter
    public String get_title() {
        return title;
    }

    public void set_title(String title) {
        this.title = title;
    }

    public String get_description() {
        return description;
    }

    public void set_description(String description) {
        this.description = description;
    }

    public String get_url() {
        return url;
    }

    public void set_url(String url) {
        this.url = url;
    }

    public String get_image() {
        return image;
    }

    public void set_image(String image) {
        this.image = image;
    }
}