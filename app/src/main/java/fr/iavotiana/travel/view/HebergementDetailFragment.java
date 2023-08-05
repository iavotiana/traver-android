package fr.iavotiana.travel.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import fr.iavotiana.travel.R;
import fr.iavotiana.travel.model.Hebergement;


public class HebergementDetailFragment extends Fragment {

    private Hebergement selectedHebergement;

    public HebergementDetailFragment(Hebergement hebergement) {
        selectedHebergement = hebergement;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hebergement_detail, container, false);

        TextView nomTextView = view.findViewById(R.id.nomTextView);
        TextView lieuTextView = view.findViewById(R.id.lieuTextView);
        TextView prixTextView = view.findViewById(R.id.prixTextView);
        TextView noteTextView = view.findViewById(R.id.noteTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
        WebView contentWebView = view.findViewById(R.id.contentWebView);

        nomTextView.setText(selectedHebergement.getNom());
        lieuTextView.setText(selectedHebergement.getLieu());
        prixTextView.setText(String.valueOf(selectedHebergement.getPrix()));
        noteTextView.setText(String.valueOf(selectedHebergement.getNote()));
        descriptionTextView.setText(selectedHebergement.getDescription());
        contentWebView.loadData(selectedHebergement.getContentWebView(), "text/html", "UTF-8");

        return view;
    }
}
