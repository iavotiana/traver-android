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
import fr.iavotiana.travel.model.Event;


public class EventDetailFragment extends Fragment {

    private Event selectedEvent;

    public EventDetailFragment(Event event) {
        selectedEvent = event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);

        TextView nomTextView = view.findViewById(R.id.nomTextView);
        TextView lieuTextView = view.findViewById(R.id.lieuTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
        WebView contentWebView = view.findViewById(R.id.contentWebView);
        ImageView imageDetail = view.findViewById(R.id.imageDetail);
        LinearLayout imageLayout = view.findViewById(R.id.imageLayout);

        nomTextView.setText(selectedEvent.getNom());
        lieuTextView.setText(selectedEvent.getLieu());
        descriptionTextView.setText(selectedEvent.getDescription());

        //String htmlContent = "<html><head><title>Événement Somaro Madagascar 2023</title><style>body {font-family: Arial, sans-serif;font-size: 16px;line-height: 1.6;color: #3498db;}.event-title {font-size: 24px;font-weight: bold;}</style></head><body><h1 class=event-title>Événement Somaro Madagascar 2023</h1>Bienvenue à l'événement Somaro Madagascar 2023,</body></html>";

        contentWebView.loadDataWithBaseURL(null, selectedEvent.getContentWebView(), "text/html", "UTF-8", null);
        System.out.println(selectedEvent.getContentWebView());
        // affichage du baner selon l'url image
        Glide.with(this)
                .load(selectedEvent.getUrlImage())
                .placeholder(R.drawable.background_event)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Optional caching strategy
                .into(imageDetail);

        int numImages = selectedEvent.getNote(); // Change this to the number of images you want to add

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
