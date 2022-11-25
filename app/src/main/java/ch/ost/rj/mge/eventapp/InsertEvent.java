package ch.ost.rj.mge.eventapp;

import static ch.ost.rj.mge.eventapp.MainActivity.logStateChange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ch.ost.rj.mge.eventapp.model.EventManager;

public class InsertEvent extends AppCompatActivity
implements AdapterView.OnItemSelectedListener {

    String[] departments = {"Alle", "Informatik", "Elektrotechnik", "WING", "EEU"};
    Calendar myCalendar = Calendar.getInstance();
    EditText text_date;
    public DrawerLayout drawerLayout_settings;
    public ActionBarDrawerToggle actionBarDrawerToggle_settings;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_event);

        EditText text_title = findViewById(R.id.text_input_title);

        Spinner spin = (Spinner) findViewById(R.id.departments_spinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, departments);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        EditText text_location = findViewById(R.id.text_input_where);

        EditText text_creator = findViewById(R.id.text_input_creator);

        TextView text_description = findViewById(R.id.text_input_description);

        Button button_gallery = findViewById(R.id.button_gallery);
        button_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });

        text_date = findViewById(R.id.text_input_date_picker);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONDAY, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };
        text_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(InsertEvent.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Button button_ok = findViewById(R.id.button_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventManager.addEvent(text_title.getText().toString(), text_date.getText().toString(),
                        text_location.getText().toString(), spin.getSelectedItem().toString(),
                        text_creator.getText().toString());

                Intent intent = new Intent(InsertEvent.this, MainActivity.class);
                Log.d("Insert Event", "new Event");
                InsertEvent.this.startActivity(intent);
            }
        }


        );
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

        //--------------------------OSTEvents Button------------------------------------------------
        MenuItem OSTEvents = findViewById(R.id.OSTEvents);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), departments[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void updateLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy", Locale.GERMAN);
        text_date.setText(dateFormat.format(myCalendar.getTime()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            ImageView imageView = findViewById(R.id.image_preview);
            imageView.setImageURI(selectedImage);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.OSTEvents:
                Intent Mainactivity = new Intent(InsertEvent.this, MainActivity.class);
                startActivity(Mainactivity);
            case R.id.einstellungen:
                Intent goToSettings = new Intent(this, Settings.class);
                this.startActivity(goToSettings);
                logStateChange("button Pressed");
                return true;

            default:
                if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

                    return true;
                }
                else{
                    return super.onOptionsItemSelected(item);
                }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
}