package ch.ost.rj.mge.eventapp;

import static ch.ost.rj.mge.eventapp.MainActivity.logStateChange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import ch.ost.rj.mge.eventapp.adapter.EventAdapter;
import ch.ost.rj.mge.eventapp.model.Event;
import ch.ost.rj.mge.eventapp.model.EventManager;

public class SavedEvents extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_events);

        ArrayList<Event> savedEvents = EventManager.getSavedEvents();

        //RecyclerView
        recyclerView = findViewById(R.id.main_feed);
        EventAdapter adapter = new EventAdapter(savedEvents, getApplication(), null);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Navigation Bar
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView bar = findViewById(R.id.navigationbar);
        bar.setNavigationItemSelectedListener(this::onOptionsItemSelected);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            if(recyclerView.getVisibility() == View.VISIBLE)
            {
              recyclerView.setVisibility(View.GONE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
            }
        }

        switch (item.getItemId()) {
            case R.id.OSTEvents:
                Intent Main = new Intent(SavedEvents.this, MainActivity.class);
                startActivity(Main);
                return true;
            case R.id.einstellungen:
                Intent goToSettings = new Intent(SavedEvents.this, Settings.class);
                this.startActivity(goToSettings);
                return true;
            case R.id.hinzugefuegte_events:
                Intent goToSavedEvents = new Intent(SavedEvents.this, SavedEvents.class);
                this.startActivity(goToSavedEvents);
                return true;

            default:
                if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
                    return true;
                } else {
                    return super.onOptionsItemSelected(item);
                }
        }
    }
}