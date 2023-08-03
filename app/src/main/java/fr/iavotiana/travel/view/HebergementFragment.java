package fr.iavotiana.travel.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.iavotiana.travel.R;
import fr.iavotiana.travel.controller.HebergementAdapter;
import fr.iavotiana.travel.model.Hebergement;


public class HebergementFragment extends Fragment {

    private ArrayList<Hebergement> hebergements;

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
         HebergementAdapter adapter = new HebergementAdapter(hebergements);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}