package fr.iavotiana.travel.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import fr.iavotiana.travel.R;
import fr.iavotiana.travel.model.Event;


public class EventDetailFragment extends Fragment {

    private Event selectedEvent;

    public EventDetailFragment(Event Event) {
        selectedEvent = Event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);

        TextView nomTextView = view.findViewById(R.id.nomTextView);
        TextView lieuTextView = view.findViewById(R.id.lieuTextView);
        TextView noteTextView = view.findViewById(R.id.noteTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
        WebView contentWebView = view.findViewById(R.id.contentWebView);

        nomTextView.setText(selectedEvent.getNom());
        lieuTextView.setText(selectedEvent.getLieu());
        noteTextView.setText(String.valueOf(selectedEvent.getNote()));
        descriptionTextView.setText(selectedEvent.getDescription());
        contentWebView.loadData(selectedEvent.getContentWebView(), "text/html", "UTF-8");

        return view;
    }
}
