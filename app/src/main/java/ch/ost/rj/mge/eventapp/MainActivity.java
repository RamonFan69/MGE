package ch.ost.rj.mge.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.util.Log;
import android.widget.Spinner;

import com.google.android.material.navigation.NavigationView;


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

        /*TODO
        Spinner spin = (Spinner) findViewById(R.id.departments_spinner);
        spin.setOnItemSelectedListener(this);
        */
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }

        switch (item.getItemId()){
            case R.id.einstellungen:
                Intent intent = new Intent(this, Settings.class);
                this.startActivity(intent);
                logStateChange("button Pressed");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
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


    public static void logStateChange(String callback) {
        Log.d("MGE.U02.DEBUG", "Method: " + callback);
    }

    private void showInsertActivity() {
        Intent intent = new Intent(this, InsertEvent.class);
        Log.d("showInsertActivity", "fab clicked");
        this.startActivity(intent);
    }
}