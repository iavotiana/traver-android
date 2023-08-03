package fr.iavotiana.travel.model;

public class Hebergement {
    private String nom;
    private String lieu;
    private String description;
    private double prix;
    private int note;

    public Hebergement(String nom, String lieu, String description, double prix, int note) {
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.prix = prix;
        this.note = note;
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

    public double getPrix() {
        return prix;
    }

    public int getNote() {
        return note;
    }
}

