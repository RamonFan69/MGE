package ch.ost.rj.mge.eventapp.model;

import android.net.Uri;

import java.util.ArrayList;

public class EventManager {
    private static ArrayList<Event> events;

    static {
        EventManager.events = new ArrayList<Event>();

        for (int i=1; i<=20; i++)
        {
            EventManager.events.add(new Event("Event " + i, "01/01/00", "location", "department", "creator", "description", null));
        }
    }

    public static ArrayList<Event> getEvents()
    {
        return EventManager.events;
    }

    public static Event getEvent(int index) { return EventManager.events.get(index); }

    public static void addEvent(String title, String date, String location, String department, String creator, String description, Uri image)
    {
        EventManager.events.add(new Event(title, date, location, department, creator, description, image));
    }
}
