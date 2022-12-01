package ch.ost.rj.mge.eventapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import ch.ost.rj.mge.eventapp.model.Event;
import ch.ost.rj.mge.eventapp.model.EventManager;

public class EventViewer extends AppCompatActivity {
    Event event;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public TextView title;
    public TextView date;
    public TextView location;
    public TextView department;
    public TextView creator;
    public TextView description;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_viewer);

        Bundle b = getIntent().getExtras();
        int index = b.getInt("Index");

        event = EventManager.getEvent(index);

        title = findViewById(R.id.text_title);
        title.setText(event.title);

        date = findViewById(R.id.text_date);
        date.setText(event.date);

        location = findViewById(R.id.text_location);
        location.setText(event.location);

        department = findViewById(R.id.text_department);
        department.setText(event.department);

        creator = findViewById(R.id.text_creator);
        creator.setText(event.creator);

        description = findViewById(R.id.text_description);
        description.setText(event.description);

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
        bar.setNavigationItemSelectedListener(this::onOptionsItemSelected);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            if (date.getVisibility() == View.VISIBLE) {
                date.setVisibility(View.GONE);
                title.setVisibility(View.GONE);
                location.setVisibility(View.GONE);
                department.setVisibility(View.GONE);
                creator.setVisibility(View.GONE);
                description.setVisibility(View.GONE);

            } else {
                date.setVisibility(View.VISIBLE);
                title.setVisibility(View.VISIBLE);
                location.setVisibility(View.VISIBLE);
                department.setVisibility(View.VISIBLE);
                creator.setVisibility(View.VISIBLE);
                description.setVisibility(View.VISIBLE);
            }
        }

        switch (item.getItemId()){
            case R.id.OSTEvents:
                Intent Mainactivity = new Intent(EventViewer.this,MainActivity.class);
                startActivity(Mainactivity);
                return true;
            case R.id.einstellungen:
                Intent settings = new Intent(EventViewer.this,Settings.class);
                startActivity(settings);
                return true;
            default:
                if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
                    return true;
                }
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
}

