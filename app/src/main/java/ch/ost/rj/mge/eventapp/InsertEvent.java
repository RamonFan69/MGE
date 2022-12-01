package ch.ost.rj.mge.eventapp;

import static ch.ost.rj.mge.eventapp.MainActivity.logStateChange;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
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
implements AdapterView.OnItemSelectedListener
{

    String[] departments = {"Alle", "Informatik", "Elektrotechnik", "WING", "EEU"};
    Calendar myCalendar = Calendar.getInstance();
    EditText text_date;
    Uri selectedImage;
    Bitmap photo;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public EditText text_title;
    public Spinner spin;
    public ArrayAdapter aa;
    public EditText text_location;
    public EditText text_creator;
    public TextView text_description;
    public Button button_gallery;
    public Button button_ok;
    public TextView title;
    public TextView when_textview;
    public TextView where_textview;
    public TextView for_who;
    public TextView creator;
    public TextView description;
    public TextView picture_uploading;
    public Button button_camera;
    public ImageView image_camera;
    private static final int REQUEST_CODE = 22;
    ImageView imageView;
    private static final int MY_CAMERA_REQUEST_CODE = 100;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_event);

        text_title = findViewById(R.id.text_input_title);

        spin = (Spinner) findViewById(R.id.departments_spinner);
        spin.setOnItemSelectedListener(this);

        aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, departments);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        text_location = findViewById(R.id.text_input_where);
        text_creator = findViewById(R.id.text_input_creator);
        text_description = findViewById(R.id.text_input_description);

        button_gallery = findViewById(R.id.button_gallery);
        button_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 3);
                    }
                    else{
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    }
                }
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

        button_ok = findViewById(R.id.button_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventManager.addEvent(text_title.getText().toString(), text_date.getText().toString(),
                        text_location.getText().toString(), spin.getSelectedItem().toString(),
                        text_creator.getText().toString(), text_description.getText().toString(), selectedImage, photo);

                Intent intent = new Intent(InsertEvent.this, MainActivity.class);
                Log.d("Insert Event", "new Event");
                InsertEvent.this.startActivity(intent);
            }
        });

        title = findViewById(R.id.textView_title);
        when_textview = findViewById(R.id.textView_when);
        where_textview = findViewById(R.id.textView_where);
        for_who = findViewById(R.id.textView_for_who);
        creator = findViewById(R.id.textView_creator);
        description = findViewById(R.id.textView_description);
        picture_uploading = findViewById(R.id.textView_upload_image);
        button_camera = findViewById(R.id.button_camera);
        imageView = findViewById(R.id.image_preview);

        button_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

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
    }

    private void takePicture() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,REQUEST_CODE);
            }
            else{
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,REQUEST_CODE);
            }
        }
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
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        logStateChange("ActivityResult wird aufgerufen");

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);

        }
        else if (resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();
            imageView.setImageURI(selectedImage);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            if (button_ok.getVisibility() == View.VISIBLE) {
                button_ok.setVisibility(View.GONE);
                spin.setVisibility(View.GONE);
                text_title.setVisibility(View.GONE);
                spin.setVisibility(View.GONE);
                text_location.setVisibility(View.GONE);
                text_creator.setVisibility(View.GONE);
                text_description.setVisibility(View.GONE);
                button_gallery.setVisibility(View.GONE);
                button_ok.setVisibility(View.GONE);
                text_date.setVisibility(View.GONE);
                title.setVisibility(View.GONE);
                when_textview.setVisibility(View.GONE);
                where_textview.setVisibility(View.GONE);
                for_who.setVisibility(View.GONE);
                creator.setVisibility(View.GONE);
                description.setVisibility(View.GONE);
                picture_uploading.setVisibility(View.GONE);
                button_camera.setVisibility(View.GONE);
            }
            else{
                button_ok.setVisibility(View.VISIBLE);
                spin.setVisibility(View.VISIBLE);
                text_title.setVisibility(View.VISIBLE);
                spin.setVisibility(View.VISIBLE);
                text_location.setVisibility(View.VISIBLE);
                text_creator.setVisibility(View.VISIBLE);
                text_description.setVisibility(View.VISIBLE);
                button_gallery.setVisibility(View.VISIBLE);
                button_ok.setVisibility(View.VISIBLE);
                text_date.setVisibility(View.VISIBLE);
                title.setVisibility(View.VISIBLE);
                when_textview.setVisibility(View.VISIBLE);
                where_textview.setVisibility(View.VISIBLE);
                for_who.setVisibility(View.VISIBLE);
                creator.setVisibility(View.VISIBLE);
                description.setVisibility(View.VISIBLE);
                picture_uploading.setVisibility(View.VISIBLE);
                button_camera.setVisibility(View.VISIBLE);
            }
        }
        switch (item.getItemId()) {
            case R.id.OSTEvents:
                Intent Main = new Intent(InsertEvent.this, MainActivity.class);
                startActivity(Main);
                return true;
            case R.id.einstellungen:
                Intent goToSettings = new Intent(this, Settings.class);
                this.startActivity(goToSettings);
                logStateChange("button Pressed");
                return true;

            default:
                if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
                    return true;
                } else {
                    return super.onOptionsItemSelected(item);
                }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] results) {
        super.onRequestPermissionsResult(requestCode, permissions, results);

        if (requestCode == 1){
            if(results[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        }
        else if (requestCode == 100){

        }
    }



}