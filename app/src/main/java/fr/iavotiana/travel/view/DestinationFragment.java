package fr.iavotiana.travel.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import fr.iavotiana.travel.R;
import fr.iavotiana.travel.controller.ListDestination;
import fr.iavotiana.travel.databinding.ActivityHomeBinding;
import fr.iavotiana.travel.model.Destination;


public class DestinationFragment extends Fragment {

    ActivityHomeBinding binding;

    public DestinationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= ActivityHomeBinding.inflate(inflater, container, false);
        getActivity().setContentView(binding.getRoot());

        ArrayList<Destination> destinationArrayList= new ArrayList<>();
        destinationArrayList.add(new Destination("title1","descri1",R.drawable.aimer,12));
        destinationArrayList.add(new Destination("title1","descri1",R.drawable.aimer,12));
        destinationArrayList.add(new Destination("title1","descri1",R.drawable.aimer,12));


        ListDestination listDest = new ListDestination(getContext(),destinationArrayList);

       // binding.;


        return binding.getRoot();
    }
}