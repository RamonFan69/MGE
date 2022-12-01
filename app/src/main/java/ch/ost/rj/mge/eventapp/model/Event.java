package ch.ost.rj.mge.eventapp.model;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.Nullable;

public class Event {
    public String title;
    public String date;
    public String location;
    public String department;
    public String creator;
    public String description;
    public Uri image;
    public Bitmap photo;

    public Event(String title, String date, String location, String department, String creator, String description, Uri image, @Nullable Bitmap photo)
    {
        this.title = title;
        this.date = date;
        this.location = location;
        this.department = department;
        this.creator = creator;
        this.description = description;
        this.image = image;
        this.photo = photo;
    }

    public String getDate()
    {
        String[] dates = this.date.split("/", 3);
        return dates[2] + dates[1] + dates[0];
    }
}
