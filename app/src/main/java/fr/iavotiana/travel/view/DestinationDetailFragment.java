package fr.iavotiana.travel.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import fr.iavotiana.travel.R;
import fr.iavotiana.travel.model.Destination;


public class DestinationDetailFragment extends Fragment {

    private Destination selectedDestination;

    public DestinationDetailFragment(Destination Destination) {
        selectedDestination = Destination;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_destination_detail, container, false);

        TextView nomTextView = view.findViewById(R.id.nomTextView);
        TextView lieuTextView = view.findViewById(R.id.lieuTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
        WebView contentWebView = view.findViewById(R.id.contentWebView);
        ImageView imageDetail = view.findViewById(R.id.imageDetail);
        LinearLayout imageLayout = view.findViewById(R.id.imageLayout);

        nomTextView.setText(selectedDestination.getNom());
        lieuTextView.setText(selectedDestination.getLieu());
        descriptionTextView.setText(selectedDestination.getDescription());
        contentWebView.loadData(selectedDestination.getContentWebView(), "text/html", "UTF-8");

        contentWebView.loadDataWithBaseURL(null, selectedDestination.getContentWebView(), "text/html", "UTF-8", null);
        System.out.println(selectedDestination.getContentWebView());
        // affichage du baner selon l'url image
        Glide.with(this)
                .load(selectedDestination.getUrlImage())
                .placeholder(R.drawable.background_event)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Optional caching strategy
                .into(imageDetail);

        int numImages =selectedDestination.getNote(); // Change this to the number of images you want to add

        for (int i = 0; i < numImages; i++) {
            ImageView imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50,50,0);
            params.setMargins(0, 0, 0, 0); // Remove margins
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.drawable.etoile); // Change to your desired image resource
            imageLayout.addView(imageView);
        }
        return view;
    }
}
