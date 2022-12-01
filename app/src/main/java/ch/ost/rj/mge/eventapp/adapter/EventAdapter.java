package ch.ost.rj.mge.eventapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.ost.rj.mge.eventapp.ClickListiner;
import ch.ost.rj.mge.eventapp.EventViewHolder;
import ch.ost.rj.mge.eventapp.R;
import ch.ost.rj.mge.eventapp.model.Event;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    public List<Event> list = Collections.emptyList();
    Context context;
    ClickListiner listener;

    public EventAdapter(List<Event> list, Context context, ClickListiner listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.event_item, parent, false);

        EventViewHolder viewHolder = new EventViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder viewHolder, int position) {
        int index = viewHolder.getAdapterPosition();
        viewHolder.title.setText(list.get(position).title);
        viewHolder.date.setText(list.get(position).date);
        viewHolder.location.setText(list.get(position).location);
        viewHolder.department.setText(list.get(position).department);
        viewHolder.creator.setText(list.get(position).creator);
        if(list.get(position).image != null) {
            viewHolder.image.setImageURI(list.get(position).image);
        }
        else if(list.get(position).photo != null) {
            viewHolder.image.setImageBitmap(list.get(position).photo);
        }
        if(listener != null) {
            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.click(index);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
