package fr.iavotiana.travel.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.iavotiana.travel.R;
import fr.iavotiana.travel.controller.HebergementAdapter;
import fr.iavotiana.travel.model.Hebergement;
import fr.iavotiana.travel.retrofit.IMyApi;
import fr.iavotiana.travel.retrofit.RetrofitClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HebergementFragment extends Fragment {

    private ArrayList<Hebergement> hebergements = new ArrayList<>();
    private HebergementAdapter adapter;
    private IMyApi iMyApi;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public HebergementFragment() {}


    private boolean isUserLoggedIn() {
        // Vérifiez si l'utilisateur est connecté en vérifiant si le token est présent dans SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        return !TextUtils.isEmpty(token);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hebergement, container, false);

        // Initialize the API service
        iMyApi = RetrofitClient.getInstance().create(IMyApi.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new HebergementAdapter(hebergements);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Set up SearchView
        // ... Votre code SearchView ...

        // Vérifiez si l'utilisateur est connecté avant d'appeler getHebergementsFromApi
        if (isUserLoggedIn()) {
            // Récupérer le token d'authentification depuis SharedPreferences
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String token = sharedPreferences.getString("token", "");

            // Charger les hébergements depuis l'API en utilisant le token d'authentification
            getHebergementsFromApi(token);
        } else {
            // L'utilisateur n'est pas connecté, afficher un message ou effectuer d'autres actions si nécessaire
            Toast.makeText(getContext(), "Vous devez vous authentifier", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void getHebergementsFromApi(String token) {
        compositeDisposable.add(iMyApi.getHebergement("Bearer " + token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        hebergementList -> {
                            hebergements.clear();
                            hebergements.addAll(hebergementList);
                            adapter.notifyDataSetChanged();
                        },
                        throwable -> {
                            Toast.makeText(getContext(), "vous deviez vous authentifier", Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    private void filterData(String query) {
        Log.d("tag= ", "filterData ********************************");
        ArrayList<Hebergement> filteredHebergements = new ArrayList<>();

        for (Hebergement hebergement : hebergements) {
            if (hebergement.getNom().toLowerCase().contains(query.toLowerCase()) ||
                    hebergement.getLieu().toLowerCase().contains(query.toLowerCase())) {
                filteredHebergements.add(hebergement);
            }
        }

        hebergements.clear();
        hebergements.addAll(filteredHebergements);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}