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
import fr.iavotiana.travel.retrofit.IMyApi;
import fr.iavotiana.travel.retrofit.RetrofitClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class DestinationFragment extends Fragment {

    private ArrayList<Destination> Destinations = new ArrayList<>();
    private ArrayList<Destination> filteredDestinations= new ArrayList<>(); // For filtered data
    private DestinationAdapter adapter;

    private IMyApi iMyApi;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();




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
                return true;
            }
        });

        // Initialize the API service
        iMyApi = RetrofitClient.getInstance().create(IMyApi.class);

        // Load destinations from API
        getDestinationsFromApi();

        return view;
    }

    private void getDestinationsFromApi() {
        compositeDisposable.add(iMyApi.getDestination()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        destinationList -> {
                            Destinations.clear(); // Clear the current list before adding new data
                            Destinations.addAll(destinationList); // Initialize Destinations list if not already initialized
                            Log.d("DestinationFragment", "Destinations fetched: " + Destinations.size());
                            adapter.notifyDataSetChanged();

                        },
                        throwable -> {
                            Log.e("DestinationFragment", "Error fetching destinations", throwable);
                        }
                ));
    }

    private void filterData(String query) {
        filteredDestinations.clear();

        for (Destination destination : Destinations) {
            if (destination.getNom().toLowerCase().contains(query.toLowerCase()) ||
                    destination.getLieu().toLowerCase().contains(query.toLowerCase())) {
                filteredDestinations.add(destination);
            }
        }
        adapter.setDestinations(filteredDestinations);
        adapter.notifyDataSetChanged();
    }

}