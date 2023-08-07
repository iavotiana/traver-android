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
    private ArrayList<Hebergement> filteredHebergements = new ArrayList<>();


    public HebergementFragment() {}


    private boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        return !TextUtils.isEmpty(token);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hebergement, container, false);


        iMyApi = RetrofitClient.getInstance().create(IMyApi.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new HebergementAdapter(hebergements);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SearchView searchView = view.findViewById(R.id.searchView);

        // Configurer le SearchView pour la recherche
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Cette méthode est appelée lorsque l'utilisateur soumet la recherche.
                // Vous pouvez effectuer la recherche ici, mais pour l'instant, nous n'en avons pas besoin car nous mettrons à jour la liste à chaque modification de texte.

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Cette méthode est appelée à chaque fois que l'utilisateur modifie le texte dans le SearchView.
                // Vous pouvez effectuer la recherche et filtrer les données ici.

                filterData(newText);

                return true;
            }
        });


        getHebergementsFromApi();
        return view;
    }

    private void getHebergementsFromApi() {
        compositeDisposable.add(iMyApi.getHebergement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        hebergementList -> {
                            hebergements.clear();
                            hebergements.addAll(hebergementList);
                            adapter.notifyDataSetChanged();
                        },
                        throwable -> {
                            Toast.makeText(getContext(), "Hebergement erreur", Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    private void filterData(String query) {
        filteredHebergements.clear();

        if (TextUtils.isEmpty(query)) {
            // Si la requête de recherche est vide, afficher tous les hébergements non filtrés
            filteredHebergements.addAll(hebergements);
        } else {
            // Filtrer les hébergements en fonction de la requête de recherche
            for (Hebergement hebergement : hebergements) {
                if (hebergement.getNom().toLowerCase().contains(query.toLowerCase()) ||
                        hebergement.getLieu().toLowerCase().contains(query.toLowerCase())) {
                    filteredHebergements.add(hebergement);
                }
            }
        }

        // Mettre à jour l'adaptateur avec les données filtrées
        adapter.setData(filteredHebergements);
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}