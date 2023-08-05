package fr.iavotiana.travel.model;

public class Event {

    private String nom;
    private String lieu;
    private String description;
    private int note;
    private String contentWebView;
    private String urlImage;

    public Event(String nom, String lieu, String description, int note, String contentWebView, String urlImage) {
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.note = note;
        this.contentWebView = contentWebView;
        this.urlImage = urlImage;
    }

    public String getNom() {
        return nom;
    }

    public String getLieu() {
        return lieu;
    }

    public String getDescription() {
        return description;
    }

    public int getNote() {
        return note;
    }

    public String getContentWebView() {
        return contentWebView;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
