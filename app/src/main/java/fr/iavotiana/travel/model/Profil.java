package fr.iavotiana.travel.model;

public class Profil {
    private Integer  poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float img;
    private String message;

    public Profil(Integer poids, Integer taille, Integer age, Integer sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calculeImg();
        this.setMessage();
    }

    private static final Integer minFemme = 15;
    private static final Integer maxFemme = 30;
    private static final Integer minHomme = 10;
    private static final Integer maxHomme = 25;


    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    private void calculeImg(){
        this.taille= this.taille/100;
        this.img = (float) ((1.2 * this.poids / (this.taille*this.taille))+(0.23 * this.age) - (10.83 * this.sexe) - 5.4);
    }

    private void setMessage(){
        if(this.sexe == 0){
            if(this.img<15){ this.message = "Trop faible";}
            if(this.img>30){ this.message = "Trop grosse";  }
            else{this.message = "normal"; }
        }
        if(this.sexe == 1){
            if(this.img<10){ this.message = "Trop faible"; }
            if(this.img>25){ this.message = "Trop grosse";  }
            else{this.message = "normal"; }
        }
    }
}
