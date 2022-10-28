package ch.ost.rj.mge.eventapp.model;

public class Event {
    public String title;
    public String date;
    public String location;
    public String department;
    public String creator;
    // TODO
    // image

    public Event(String title, String department)
    {
        this.title = title;
        this.department = department;
    }
}
