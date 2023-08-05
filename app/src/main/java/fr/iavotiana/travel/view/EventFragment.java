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
import fr.iavotiana.travel.controller.EventAdapter;
import fr.iavotiana.travel.model.Event;


public class EventFragment extends Fragment {

    private ArrayList<Event> Events;
    private ArrayList<Event> filteredEvents= new ArrayList<>(); // For filtered data
    private EventAdapter adapter;


    public EventFragment() {
        this.Events = new ArrayList<>();
        Events.add(new Event("Hotel A", "City X", "Luxurious hotel", 4,"<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event1"));
        Events.add(new Event("Guesthouse B", "City Y", "Cozy guesthouse", 3, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event2"));
        Events.add(new Event("Resort C", "City Z", "Beachfront resort", 5, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event3"));
        Events.add(new Event("Hotel A", "City X", "Luxurious hotel", 4, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event4"));
        Events.add(new Event("Guesthouse B", "City Y", "Cozy guesthouse", 3, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event1"));
        Events.add(new Event("Resort C", "City Z", "Beachfront resort", 5, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event2"));
        Events.add(new Event("Hotel A", "City X", "Luxurious hotel", 4, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event3"));
        Events.add(new Event("Guesthouse B", "City Y", "Cozy guesthouse", 3, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event4"));
        Events.add(new Event("Resort C", "City Z", "Beachfront resort", 5, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event1"));
        Events.add(new Event("Hotel A", "City X", "Luxurious hotel", 4, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event2"));
        Events.add(new Event("Guesthouse B", "City Y", "Cozy guesthouse", 3, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event3"));
        Events.add(new Event("Resort C", "City Z", "Beachfront resort" , 5, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event4"));

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new EventAdapter(Events);
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
        if (filteredEvents.size() != 0) {
            filteredEvents.clear();
            Log.d("tag= ", "filteredEvents != null");
        }


        for (Event Event : Events) {
            if (Event.getNom().toLowerCase().contains(query.toLowerCase()) ||
                    Event.getLieu().toLowerCase().contains(query.toLowerCase())) {
                filteredEvents.add(Event);
            }
        }
        adapter= new EventAdapter(filteredEvents);
        adapter.notifyDataSetChanged();
    }
}