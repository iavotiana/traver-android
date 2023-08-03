package fr.iavotiana.travel.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import fr.iavotiana.travel.R;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (savedInstanceState == null) {
            // Add the default fragment when the activity is first created
            Fragment defaultFragment = new AcceuilFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, defaultFragment)
                    .commit();
        }

        toolbar = findViewById(R.id.toolbar);
        drawerLayout= findViewById(R.id.drawerLayout);
        navigation = findViewById(R.id.navigation);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NotNull MenuItem item){

                switch(item.getItemId()){
                    case R.id.menu_connection:
                        fragmentR(new ConnectionFragment());
                        drawerLayout.closeDrawer((GravityCompat.START));
                        //Toast.makeText(HomeActivity.this,"connection", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_destination:
                        fragmentR(new DestinationFragment());
                        drawerLayout.closeDrawer((GravityCompat.START));
                       // Toast.makeText(HomeActivity.this,"destination", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_hebergement:
                        fragmentR(new HebergementFragment());
                        drawerLayout.closeDrawer((GravityCompat.START));
                        //Toast.makeText(HomeActivity.this,"Hebergement", Toast.LENGTH_SHORT).show();

                        break;

                    default:
                        fragmentR(new AcceuilFragment());
                        drawerLayout.closeDrawer((GravityCompat.START));
                       // Toast.makeText(HomeActivity.this,"acceuil", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        });
    }

    public void fragmentR(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Hide the current fragment (if any)
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.frame);
        if (currentFragment != null) {
            fragmentTransaction.remove(currentFragment);
        }

        // Add the new fragment
        fragmentTransaction.add(R.id.frame, fragment);

        fragmentTransaction.commit();
    }
}