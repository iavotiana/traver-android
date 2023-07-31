package fr.iavotiana.travel.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import fr.iavotiana.travel.R;
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed((Runnable) () -> {
            Intent intent = new Intent(SplashActivity.this , HomeActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}