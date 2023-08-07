package fr.iavotiana.travel.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import fr.iavotiana.travel.R;
import fr.iavotiana.travel.model.Event;
import fr.iavotiana.travel.view.EventDetailFragment;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private ArrayList<Event> Events;

    public EventAdapter(ArrayList<Event> Events) {
        this.Events = Events;
    }

    public void setEvents(ArrayList<Event> newEvents) {
        this.Events = newEvents;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public EventViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull EventViewHolder holder, int position) {
        final Event event = Events.get(position);

        holder.nomTextView.setText(event.getNom());
        holder.lieuTextView.setText(event.getLieu());
        holder.descriptionTextView.setText(String.valueOf(event.getNote()));
        Picasso.get().load(event.getUrlImage()).into(holder.imagelist);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click by opening the EventDetailFragment
                FragmentTransaction transaction = ((AppCompatActivity) v.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.frame, new EventDetailFragment(event));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Events.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionTextView;
        TextView nomTextView;
        TextView lieuTextView;
        ImageView imagelist;

        // affichage du baner selon l'url image


        EventViewHolder(View itemView) {
            super(itemView);
            nomTextView = itemView.findViewById(R.id.nomTextView);
            lieuTextView = itemView.findViewById(R.id.lieuTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            imagelist = itemView.findViewById(R.id.imagelist);
        }

    }
}

