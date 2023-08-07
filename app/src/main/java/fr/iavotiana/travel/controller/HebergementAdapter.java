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
import fr.iavotiana.travel.model.Hebergement;
import fr.iavotiana.travel.view.HebergementDetailFragment;

public class HebergementAdapter extends RecyclerView.Adapter<HebergementAdapter.HebergementViewHolder> {

    private ArrayList<Hebergement> hebergements;

    public HebergementAdapter(ArrayList<Hebergement> hebergements) {
        this.hebergements = hebergements;
    }

    public void setData(ArrayList<Hebergement> filteredHebergements) {
        this.hebergements = filteredHebergements;
        notifyDataSetChanged(); // Notifiez l'adaptateur que les données ont changé pour rafraîchir l'affichage
    }

    @NotNull
    @Override
    public HebergementViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hebergement, parent, false);
        return new HebergementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull HebergementViewHolder holder, int position) {
        final Hebergement hebergement = hebergements.get(position);

        holder.nomTextView.setText(hebergement.getNom());
        holder.lieuTextView.setText(hebergement.getLieu());

        Picasso.get().load(hebergement.getUrlImage()).into(holder.imagelist);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click by opening the HebergementDetailFragment
                FragmentTransaction transaction = ((AppCompatActivity) v.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.frame, new HebergementDetailFragment(hebergement));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return hebergements.size();
    }

    static class HebergementViewHolder extends RecyclerView.ViewHolder {
        TextView nomTextView;
        TextView lieuTextView;
        ImageView imagelist;
        HebergementViewHolder(View itemView) {
            super(itemView);
            nomTextView = itemView.findViewById(R.id.nomTextView);
            lieuTextView = itemView.findViewById(R.id.lieuTextView);
            imagelist = itemView.findViewById(R.id.imagelist);
        }
    }
}

