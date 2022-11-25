package ch.ost.rj.mge.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.view.Menu;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ch.ost.rj.mge.eventapp.model.Event;
import ch.ost.rj.mge.eventapp.model.EventManager;
import ch.ost.rj.mge.eventapp.adapter.EventAdapter;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener{
    private View insertButton;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up fab to switch activity
        insertButton = findViewById(R.id.fab);
        insertButton.setOnClickListener(v -> showInsertActivity());

        // Objekte / Events in Main Feed
        RecyclerView recyclerView = findViewById(R.id.main_feed);

        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        ArrayList<Event> data = EventManager.getEvents();
        EventAdapter adapter = new EventAdapter(data);
        recyclerView.setAdapter(adapter);


        /*TODO
        Spinner spin = (Spinner) findViewById(R.id.departments_spinner);
        spin.setOnItemSelectedListener(this);
        */
        // -------------------- Navigation Bar ----------------------------------
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView bar = findViewById(R.id.navigationbar);
        bar.setNavigationItemSelectedListener(this);
    }

    //------------------- Event Adder ----------------------------------------------
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    } */

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        RecyclerView eventsHide = findViewById(R.id.main_feed);

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            if (eventsHide.getVisibility() == View.VISIBLE){
                eventsHide.setVisibility(View.GONE);
                return true;
            }
            else{
                eventsHide.setVisibility(View.VISIBLE);
                return true;
            }

        }


        return super.onOptionsItemSelected(item);
    }
//------------------------------ Auswahl in der Navigationbar -----------------------
    @Override
    public boolean onNavigationItemSelected(MenuItem Item) {
        logStateChange("Methode erreicht");
        switch (Item.getItemId()) {
            case R.id.einstellungen:
                Intent goToSettings = new Intent(this, Settings.class);
                this.startActivity(goToSettings);
                logStateChange("button Pressed");
                return true;
            case R.id.hinzugefuegte_events:
                //Intent intent = new Intent(this,);
        }
        return false;
    }


    public static void logStateChange(String callback) {
        Log.d("MGE.U02.DEBUG", "Method: " + callback);
    }

    private void showInsertActivity() {
        logStateChange("Funktion zum InsertEvent wird aufgerufen");
        Intent intent = new Intent(this, InsertEvent.class);
        Log.d("showInsertActivity", "fab clicked");
        this.startActivity(intent);
    }
}