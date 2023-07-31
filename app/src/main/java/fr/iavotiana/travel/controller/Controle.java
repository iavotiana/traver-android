package fr.iavotiana.travel.controller;

import fr.iavotiana.travel.model.Profil;

public final class Controle {
    private static Controle instance = null;
    private Profil profil;


    private Controle(){
        super();
    }

    /**
     * creation d'un instance
     * @return
     */
    public static final Controle getInstance(){
        if(Controle.instance == null){
            Controle.instance = new Controle();
        }
        return Controle.instance;
    }

    /**
     * Creation du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme et 0 pour femme
     * @return Profil
     */
    public Profil createProfil(Integer poids, Integer taille, Integer age, Integer sexe){
        profil= new Profil(poids, taille,age,sexe);
        return profil;
    }

}
