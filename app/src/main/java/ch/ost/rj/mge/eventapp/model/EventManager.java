package ch.ost.rj.mge.eventapp.model;

import android.net.Uri;

import java.util.ArrayList;

public class EventManager {
    private static ArrayList<Event> events;
    private static ArrayList<Event> filtered_events;

    static {
        EventManager.events = new ArrayList<>();

        EventManager.events.add(new Event("Event 1", "08/12/22", "Geb. 3", "Alle", "H. Muster",
                "Description 1", null));
        EventManager.events.add(new Event("Event 2", "09/12/22", "ZAK", "Informatik", "A. Müller",
                "Description 2", null));
        EventManager.events.add(new Event("Event 3", "02/12/22", "Geb. 1", "Elektrotechnik", "J. Schmid",
                "Description 3", null));
        EventManager.events.add(new Event("Event 4", "23/12/22", "OST", "Alle", "B. Meier",
                "Description 4", null));
        EventManager.events.add(new Event("Event 5", "16/12/22", "Geb. 5", "Alle", "kreativ",
                "Description 5", null));
        /*
        for (int i=1; i<=20; i++)
        {
            EventManager.events.add(new Event("Event " + i, "01/01/00", "location", "department", "creator", "description", null));
        }
        */
    }

    public static ArrayList<Event> getEvents(String dep)
    {
        filtered_events = new ArrayList<>();

        if(dep.equals("Alle"))
        {
            return EventManager.events;
        }
        else
        {
            for(int i=0; i<events.size(); i++)
            {
                if(EventManager.events.get(i).department.equals(dep))
                {
                    filtered_events.add(EventManager.events.get(i));
                }
            }
            return filtered_events;
        }
    }

    public static Event getEvent(int index, String dep)
    {
        if(dep.equals("Alle")) {
            return EventManager.events.get(index);
        }
        else
        {
            return EventManager.filtered_events.get(index);
        }
    }

    public static void addEvent(String title, String date, String location, String department, String creator, String description, Uri image)
    {
        EventManager.events.add(new Event(title, date, location, department, creator, description, image));
    }
}
