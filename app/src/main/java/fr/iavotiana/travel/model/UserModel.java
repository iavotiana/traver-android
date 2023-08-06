package fr.iavotiana.travel.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserModel {
    @SerializedName("nom")
    private String nom;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}

