package ch.ost.rj.mge.eventapp;




import static ch.ost.rj.mge.eventapp.MainActivity.logStateChange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.material.navigation.NavigationView;

public class Settings extends AppCompatActivity {

    public DrawerLayout drawerLayout_settings;
    public ActionBarDrawerToggle actionBarDrawerToggle_settings;
    Switch Darkmode;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        DrawerLayout drawerLayout_settings = findViewById(R.id.my_drawer_layout_settings);
        actionBarDrawerToggle_settings = new ActionBarDrawerToggle(this, drawerLayout_settings, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout_settings.addDrawerListener(actionBarDrawerToggle_settings);
        actionBarDrawerToggle_settings.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView bar = findViewById(R.id.navigation_bar_settings);
        bar.setNavigationItemSelectedListener(this::onOptionsItemSelected);

        //--------------------------OSTEvents Button------------------------------------------------
        MenuItem OstEvents = findViewById(R.id.OSTEvents);
        //------------------------- Switch for Darkmode --------------------------------------------
        Darkmode = findViewById(R.id.Darkmode);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        Darkmode.setChecked(sharedPreferences.getBoolean("value",false));
        Darkmode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    Darkmode.setChecked(true);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    Darkmode.setChecked(false);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.OSTEvents:
                Intent Mainactivity = new Intent(Settings.this,MainActivity.class);
                startActivity(Mainactivity);
            default:
                if (actionBarDrawerToggle_settings.onOptionsItemSelected(item)) {
                    onNavigationItemSelected(item);
                    return true;
                }
                return super.onOptionsItemSelected(item);
        }

    }


    public boolean onNavigationItemSelected(MenuItem Item) {

        logStateChange("Methode erreicht");

        return false;
    }










}