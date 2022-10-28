package ch.ost.rj.mge.eventapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ch.ost.rj.mge.eventapp.model.Event;

public class EventAdapter extends ArrayAdapter<Event> {

    public EventAdapter(Context context, ArrayList<Event> events) {
        super(context, 0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            Context context = getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            convertView = inflater.inflate(
                    android.R.layout.simple_list_item_2,
                    parent,
                    false);

            viewHolder = new ViewHolder();
            viewHolder.text1 = convertView.findViewById(android.R.id.text1);
            // viewHolder.text2 = convertView.findViewById(android.R.id.text2);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Event event = getItem(position);
        viewHolder.text1.setText(event.title);
        // viewHolder.text2.setText(event.creator);

        return convertView;
    }

    private class ViewHolder {
        TextView text1;
        // TextView text2;
    }
}
