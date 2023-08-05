package fr.iavotiana.travel.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.iavotiana.travel.R;
import fr.iavotiana.travel.controller.HebergementAdapter;
import fr.iavotiana.travel.model.Hebergement;


public class HebergementFragment extends Fragment {

    private ArrayList<Hebergement> hebergements;
    private ArrayList<Hebergement> filteredHebergements= new ArrayList<>(); // For filtered data
    private HebergementAdapter adapter;


    public HebergementFragment() {
        this.hebergements = new ArrayList<>();
        hebergements.add(new Hebergement("Hotel A", "City X", "Luxurious hotel", 150.0, 4));
        hebergements.add(new Hebergement("Guesthouse B", "City Y", "Cozy guesthouse", 80.0, 3));
        hebergements.add(new Hebergement("Resort C", "City Z", "Beachfront resort", 200.0, 5));
        hebergements.add(new Hebergement("Hotel A", "City X", "Luxurious hotel", 150.0, 4));
        hebergements.add(new Hebergement("Guesthouse B", "City Y", "Cozy guesthouse", 80.0, 3));
        hebergements.add(new Hebergement("Resort C", "City Z", "Beachfront resort", 200.0, 5));
        hebergements.add(new Hebergement("Hotel A", "City X", "Luxurious hotel", 150.0, 4));
        hebergements.add(new Hebergement("Guesthouse B", "City Y", "Cozy guesthouse", 80.0, 3));
        hebergements.add(new Hebergement("Resort C", "City Z", "Beachfront resort", 200.0, 5));
        hebergements.add(new Hebergement("Hotel A", "City X", "Luxurious hotel", 150.0, 4));
        hebergements.add(new Hebergement("Guesthouse B", "City Y", "Cozy guesthouse", 80.0, 3));
        hebergements.add(new Hebergement("Resort C", "City Z", "Beachfront resort", 200.0, 5));

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hebergement, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new HebergementAdapter(hebergements);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Set up SearchView
        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                return true;
            }
        });

        return view;
    }

    private void filterData(String query) {
        Log.d("tag= ", "filterData ********************************");
        if (filteredHebergements.size() != 0) {
            filteredHebergements.clear();
            Log.d("tag= ", "filteredHebergements != null");
        }


        for (Hebergement hebergement : hebergements) {
            if (hebergement.getNom().toLowerCase().contains(query.toLowerCase()) ||
                    hebergement.getLieu().toLowerCase().contains(query.toLowerCase())) {
                filteredHebergements.add(hebergement);
            }
        }
        adapter= new HebergementAdapter(filteredHebergements);
        adapter.notifyDataSetChanged();
    }
}