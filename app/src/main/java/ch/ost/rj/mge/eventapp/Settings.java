package ch.ost.rj.mge.eventapp;




import static ch.ost.rj.mge.eventapp.MainActivity.logStateChange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class Settings extends AppCompatActivity {

    public DrawerLayout drawerLayout_settings;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout_settings = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout_settings, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout_settings.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    public boolean onNavigationItemSelected(MenuItem Item) {
        logStateChange("Methode erreicht");
        switch (Item.getItemId()) {
            case R.id.einstellungen:
                Intent intent = new Intent(this, Settings.class);
                this.startActivity(intent);
                logStateChange("button Pressed");
                return true;
            case R.id.hinzugefuegte_events:
                //Intent intent = new Intent(this,);
        }
        return false;
    }










}