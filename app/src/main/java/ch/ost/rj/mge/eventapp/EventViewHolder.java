package ch.ost.rj.mge.eventapp;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView date;
    public TextView location;
    public TextView department;
    public TextView creator;
    public View view;

    public EventViewHolder(View itemView)
    {
        super(itemView);

        title = itemView.findViewById(R.id.text_title);
        date = itemView.findViewById(R.id.text_date);
        location = itemView.findViewById(R.id.text_location);
        department = itemView.findViewById(R.id.text_department);
        creator = itemView.findViewById(R.id.text_creator);
        view = itemView;
    }
}
