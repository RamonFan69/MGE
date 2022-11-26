package ch.ost.rj.mge.eventapp.model;

import android.net.Uri;

public class Event {
    public String title;
    public String date;
    public String location;
    public String department;
    public String creator;
    public String description;
    public Uri image;

    public Event(String title, String date, String location, String department, String creator, String description, Uri image)
    {
        this.title = title;
        this.date = date;
        this.location = location;
        this.department = department;
        this.creator = creator;
        this.description = description;
        this.image = image;
    }
}
