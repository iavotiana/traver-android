package fr.iavotiana.travel.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.iavotiana.travel.R;
import fr.iavotiana.travel.controller.Controle;
import fr.iavotiana.travel.model.Profil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        this.controle= Controle.getInstance();
    }

    // propriete
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioGroup grbSexe;
    private RadioButton rdHomme;
    private ImageView imgEmoji;
    private TextView txtResultat;

    private Controle controle;

    /**
     * initialization of the graphic object
     *
     */
    private void init(){
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        imgEmoji= (ImageView) findViewById(R.id.imgEmoji);
        txtResultat = (TextView) findViewById(R.id.txtResultat);
        clickCalcule();
    }

    /**
     * Ecoute l'evenement lorsqu'on click le boutton calcule
     */
    private void clickCalcule(){
        ((Button) findViewById(R.id.btnCalcule)).setOnClickListener(v -> {
            // Toast.makeText(MainActivity.this, "test 123", Toast.LENGTH_SHORT).show();
           // Log.d("message","click calculi *******************************");
            Integer poids=  0;
            Integer taille=  0;
            Integer age=   0;
            Integer sexe=  0;

            try{
                poids = Integer.parseInt(txtPoids.getText().toString());
                taille = Integer.parseInt(txtTaille.getText().toString());
                age = Integer.parseInt(txtAge.getText().toString());
                if(rdHomme.isChecked() ){
                    sexe= 1;
                }
                showResult(poids,taille,age,sexe);
               Intent intent = new Intent(MainActivity.this , HomeActivity.class);
                startActivity(intent);
            }catch (Exception e){
                Log.d("exeption",e.toString());
                Toast.makeText(MainActivity.this, "Veuillez verifier votre Saisie", Toast.LENGTH_SHORT).show();
            }


        });
    }

    /**
     * affichage du resultat de l'IMG
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void showResult(Integer poids, Integer taille, Integer age, Integer sexe){
        // Creation du profil
        Profil profil= this.controle.createProfil(poids,taille,age,sexe);
        float img= profil.getImg();
        String message= profil.getMessage();

        //affichage
        if(message == "normal"){
            imgEmoji.setImageResource(R.drawable.aimer);

        }else{
            imgEmoji.setImageResource(R.drawable.rigoler);
        }
        txtResultat.setText(message);

    }

}