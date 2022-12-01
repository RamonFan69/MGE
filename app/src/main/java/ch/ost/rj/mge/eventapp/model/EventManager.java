package ch.ost.rj.mge.eventapp.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;

import java.util.ArrayList;

public class EventManager {
    private static ArrayList<Event> events;
    private static ArrayList<Event> filtered_events;
    private static ArrayList<Event> savedEvents = new ArrayList<>();

    static {
        EventManager.events = new ArrayList<>();

        EventManager.events.add(new Event("Event 1", "08/12/22", "Geb. 3", "Alle", "H. Muster",
                "Description 1", null,null));
        EventManager.events.add(new Event("Event 2", "09/12/22", "ZAK", "Informatik", "A. Müller",
                "Description 2", null,null));
        EventManager.events.add(new Event("Event 3", "02/12/22", "Geb. 1", "Elektrotechnik", "J. Schmid",
                "Description 3", null,null));
        EventManager.events.add(new Event("Event 4", "23/12/22", "OST", "Alle", "B. Meier",
                "Description 4", null,null));
        EventManager.events.add(new Event("Event 5", "16/12/22", "Geb. 5", "Alle", "kreativ",
                "Description 5", null,null));
        EventManager.events.add(new Event("Event 6", "15/12/22", "OST", "EEU", "C. Schmied",
                "Description 6", null, null));
    }

    public static ArrayList<Event> getEvents(String dep)
    {
        filtered_events = new ArrayList<>();

        if(dep.equals("Alle"))
        {
            EventManager.sort(events);
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
            EventManager.sort(filtered_events);
            return filtered_events;
        }
    }

    public static void sort(ArrayList<Event> list)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list.sort((e1, e2) -> e1.getDate().compareTo(e2.getDate()));
        }
    }

    public static Event getEvent(int index, String dep)
    {
        if(dep.equals("Alle"))
        {
            return EventManager.events.get(index);
        }
        else
        {
            return EventManager.getEvents(dep).get(index);
        }
    }

    public static void addEvent(String title, String date, String location, String department, String creator, String description, Uri image, Bitmap photo)
    {
        EventManager.events.add(new Event(title, date, location, department, creator, description, image,photo));
    }

    public static void saveEvent(Event e)
    {
        EventManager.savedEvents.add(e);
    }

    public static ArrayList<Event> getSavedEvents()
    {
        return EventManager.savedEvents;
    }
}
