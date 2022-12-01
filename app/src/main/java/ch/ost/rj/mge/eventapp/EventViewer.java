package ch.ost.rj.mge.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import ch.ost.rj.mge.eventapp.model.Event;
import ch.ost.rj.mge.eventapp.model.EventManager;

public class EventViewer extends AppCompatActivity {
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_viewer);

        Bundle b = getIntent().getExtras();
        int index = b.getInt("Index");
        String dep = b.getString("Dep");

        event = EventManager.getEvent(index, dep);

        TextView title = findViewById(R.id.text_title);
        title.setText(event.title);

        TextView date = findViewById(R.id.text_date);
        date.setText(event.date);

        TextView location = findViewById(R.id.text_location);
        location.setText(event.location);

        TextView department = findViewById(R.id.text_department);
        department.setText(event.department);

        TextView creator = findViewById(R.id.text_creator);
        creator.setText(event.creator);

        TextView description = findViewById(R.id.text_description);
        description.setText(event.description);
    }
}