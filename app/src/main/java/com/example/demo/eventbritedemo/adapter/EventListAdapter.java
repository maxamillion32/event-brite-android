package com.example.demo.eventbritedemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.activity.EventDetailActivity;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.utility.Constants;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {

    private final LayoutInflater inflater;
    private Context context;
    private List<EventResponseModel.EventsEntity> list;

    public EventListAdapter(Context context, List<EventResponseModel.EventsEntity> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventViewHolder(inflater.inflate(R.layout.event_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        final EventResponseModel.EventsEntity eventsEntity = list.get(position);
        holder.txtEventName.setText(eventsEntity.getName().getHtml());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtEventName;

        public EventViewHolder(View itemView) {
            super(itemView);
            txtEventName = (TextView) itemView.findViewById(R.id.txtEventName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent intent = new Intent(context, EventDetailActivity.class);
                    intent.putExtra(Constants.IntentKeys.EVENT_ID,
                            list.get(getLayoutPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
