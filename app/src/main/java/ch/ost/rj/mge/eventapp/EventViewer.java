package ch.ost.rj.mge.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EventViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_viewer);

        Bundle b = getIntent().getExtras();
        int index = b.getInt("Index");

        TextView textView = findViewById(R.id.text_index);
        textView.setText(Integer.toString(index));
    }
}