package ch.ost.rj.mge.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.MediaRouteButton;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import ch.ost.rj.mge.eventapp.model.Event;
import ch.ost.rj.mge.eventapp.model.EventManager;
import ch.ost.rj.mge.eventapp.adapter.EventAdapter;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {
    private View insertButton;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public Spinner spin;
    EventAdapter adapter;
    String[] departments = {"Alle", "Informatik", "Elektrotechnik", "WING", "EEU"};
    String dep_filter = "Alle";
    public TextView choosedepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up fab to switch activity
        insertButton = findViewById(R.id.fab);
        insertButton.setOnClickListener(v -> showInsertActivity());

        // Objekte / Events in Main Feed
        List<Event> events = new ArrayList<>();
        events = EventManager.getEvents(dep_filter);
        ClickListiner listiner = new ClickListiner() {
            @Override
            public void click(int index) {
                Intent intent = new Intent(MainActivity.this, EventViewer.class);
                Bundle b = new Bundle();
                b.putInt("Index", index);
                b.putString("Dep", dep_filter);
                intent.putExtras(b);
                MainActivity.this.startActivity(intent);
            }
        };
        RecyclerView recyclerView = findViewById(R.id.main_feed);
        adapter = new EventAdapter(events, getApplication(), listiner);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        choosedepartment = findViewById(R.id.text_choose_department);
        spin = (Spinner) findViewById(R.id.departments_spinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, departments);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        String department = spin.getSelectedItem().toString();

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

    //Department Spinner
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        logStateChange("onItemSelected");
        Toast.makeText(getApplicationContext(), departments[position], Toast.LENGTH_LONG).show();
        adapter.list = EventManager.getEvents(departments[position]);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    // override the onOptionsItemSelected() function to implement the item click listener callback
    // to open and close the navigation drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        RecyclerView eventsHide = findViewById(R.id.main_feed);

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            if (eventsHide.getVisibility() == View.VISIBLE) {
                eventsHide.setVisibility(View.GONE);
                spin.setVisibility(View.GONE);
                choosedepartment.setVisibility(View.GONE);
            } else {
                eventsHide.setVisibility(View.VISIBLE);
                choosedepartment.setVisibility(View.VISIBLE);
                spin.setVisibility(View.VISIBLE);
            }
            return true;
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
                Intent goToSavedEvents = new Intent(this, SavedEvents.class);
                this.startActivity(goToSavedEvents);
                return true;
        }
        return false;
    }

    public static void logStateChange(String callback) {
        Log.d("MGE.U02.DEBUG", "Method: " + callback);
    }

    private void showInsertActivity() {
        Intent intent = new Intent(this, InsertEvent.class);
        // Log.d("showInsertActivity", "fab clicked");
        this.startActivity(intent);
    }
}