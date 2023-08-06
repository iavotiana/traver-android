package fr.iavotiana.travel.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import fr.iavotiana.travel.R;
import fr.iavotiana.travel.model.Destination;
import fr.iavotiana.travel.view.DestinationDetailFragment;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder> {

    private ArrayList<Destination> Destinations =new ArrayList<>();


    public DestinationAdapter(ArrayList<Destination> Destinations) {
        this.Destinations = Destinations;
    }
    public void setDestinations(List<Destination> destinations) {
        this.Destinations = new ArrayList<>(destinations);
    }

    @NotNull
    @Override
    public DestinationViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_destination, parent, false);
        return new DestinationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull DestinationViewHolder holder, int position) {
        final Destination Destination = Destinations.get(position);

        holder.nomTextView.setText(Destination.getNom());
        holder.lieuTextView.setText(Destination.getLieu());
        holder.noteTextView.setText(String.valueOf(Destination.getNote()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click by opening the DestinationDetailFragment
                FragmentTransaction transaction = ((AppCompatActivity) v.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.frame, new DestinationDetailFragment(Destination));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Destinations.size();
    }

    static class DestinationViewHolder extends RecyclerView.ViewHolder {
        TextView nomTextView;
        TextView lieuTextView;
        TextView prixTextView;
        TextView noteTextView;

        DestinationViewHolder(View itemView) {
            super(itemView);
            nomTextView = itemView.findViewById(R.id.nomTextView);
            lieuTextView = itemView.findViewById(R.id.lieuTextView);
            prixTextView = itemView.findViewById(R.id.prixTextView);
            noteTextView = itemView.findViewById(R.id.noteTextView);
        }
    }
}

