package ch.ost.rj.mge.eventapp.model;

import java.util.ArrayList;

public class EventManager {
    private static ArrayList<Event> events;

    static {
        EventManager.events = new ArrayList<Event>();

        for (int i=1; i<=20; i++)
        {
            EventManager.events.add(new Event("Event " + i, "01/01/00", "location", "department", "creator"));
        }
    }

    public static ArrayList<Event> getEvents()
    {
        return EventManager.events;
    }

    public static void addEvent(String title, String date, String location, String department, String creator)
    {
        EventManager.events.add(new Event(title, date, location, department, creator));
    }
}
