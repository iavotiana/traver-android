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
import fr.iavotiana.travel.retrofit.IMyApi;
import fr.iavotiana.travel.retrofit.RetrofitClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class EventFragment extends Fragment {


    private ArrayList<Event> Events = new ArrayList<>();
    private ArrayList<Event> filteredEvents = new ArrayList<>();
    private EventAdapter adapter;

    private IMyApi iMyApi;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new EventAdapter(Events);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText);
                return true;
            }
        });

        iMyApi = RetrofitClient.getInstance().create(IMyApi.class);

        // Load events from API
        getEventsFromApi();

        return view;
    }

    private void getEventsFromApi() {
        compositeDisposable.add(iMyApi.getEvent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        eventList -> {
                            Events = (ArrayList<Event>) eventList;
                            adapter.setEvents(Events);
                            adapter.notifyDataSetChanged();
                            Log.d("EventFragment", "Events fetched: " + Events.size());
                        },
                        throwable -> {
                            Log.e("EventFragment", "Error fetching events", throwable);
                        }
                ));
    }

    private void filterData(String query) {
        filteredEvents.clear();

        for (Event event : Events) {
            if (event.getNom().toLowerCase().contains(query.toLowerCase()) ||
                    event.getLieu().toLowerCase().contains(query.toLowerCase())) {
                filteredEvents.add(event);
            }
        }
        adapter.setEvents(filteredEvents);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear(); // Clear disposables to prevent memory leaks
    }
}