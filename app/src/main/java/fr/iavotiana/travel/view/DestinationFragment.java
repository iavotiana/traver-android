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
import fr.iavotiana.travel.controller.DestinationAdapter;
import fr.iavotiana.travel.model.Destination;


public class DestinationFragment extends Fragment {

    private ArrayList<Destination> Destinations;
    private ArrayList<Destination> filteredDestinations= new ArrayList<>(); // For filtered data
    private DestinationAdapter adapter;


    public DestinationFragment() {
        this.Destinations = new ArrayList<>();
        Destinations.add(new Destination("Hotel A", "City X", "Luxurious hotel", 4,"<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Destination1"));
        Destinations.add(new Destination("Guesthouse B", "City Y", "Cozy guesthouse", 3, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Destination2"));
        Destinations.add(new Destination("Resort C", "City Z", "Beachfront resort", 5, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Destination3"));
        Destinations.add(new Destination("Hotel A", "City X", "Luxurious hotel", 4, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Destination4"));
        Destinations.add(new Destination("Guesthouse B", "City Y", "Cozy guesthouse", 3, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Destination1"));
        Destinations.add(new Destination("Resort C", "City Z", "Beachfront resort", 5, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Destination2"));
        Destinations.add(new Destination("Hotel A", "City X", "Luxurious hotel", 4, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Destination3"));
        Destinations.add(new Destination("Guesthouse B", "City Y", "Cozy guesthouse", 3, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Destination4"));
        Destinations.add(new Destination("Resort C", "City Z", "Beachfront resort", 5, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Destination1"));
        Destinations.add(new Destination("Hotel A", "City X", "Luxurious hotel", 4, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Destination2"));
        Destinations.add(new Destination("Guesthouse B", "City Y", "Cozy guesthouse", 3, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Destination3"));
        Destinations.add(new Destination("Resort C", "City Z", "Beachfront resort" , 5, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Destination4"));

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_destination, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new DestinationAdapter(Destinations);
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
        if (filteredDestinations.size() != 0) {
            filteredDestinations.clear();
            Log.d("tag= ", "filteredDestinations != null");
        }


        for (Destination Destination : Destinations) {
            if (Destination.getNom().toLowerCase().contains(query.toLowerCase()) ||
                    Destination.getLieu().toLowerCase().contains(query.toLowerCase())) {
                filteredDestinations.add(Destination);
            }
        }
        adapter= new DestinationAdapter(filteredDestinations);
        adapter.notifyDataSetChanged();
    }
}