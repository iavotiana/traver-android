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


public class EventFragment extends Fragment {

    private ArrayList<Event> Events;
    private ArrayList<Event> filteredEvents= new ArrayList<>(); // For filtered data
    private EventAdapter adapter;


    public EventFragment() {
        String htmlContentSomaro = "<!DOCTYPE html><html><head><title>Événement Somaro Madagascar 2023</title><style>body{font-family: Arial, sans-serif;font-size: 16px;line-height: 1.6}.event-title{font-size: 24px;font-weight: bold;color: #3498db;}.important-text{font-weight: bold;color: #e74c3c;}</style></head><body><h1 class=event-title>Événement Somaro Madagascar 2023</h1><p>Bienvenue à l'événement <span class=important-text>Somaro Madagascar 2023</span>, le rendez-vous incontournable de l'année pour les passionnés de technologie et d'innovation.</p><p>Cet événement exceptionnel se tiendra du <span class=important-text>10 au 15 septembre 2023</span> à Antananarivo, la capitale enchanteresse de Madagascar. Rejoignez-nous pour cinq jours d'apprentissage, de réseautage et d'exploration des dernières avancées dans le monde de la technologie.</p><p>Nos conférenciers invités, des experts renommés dans leurs domaines, partageront leurs connaissances sur des sujets tels que l'intelligence artificielle, la réalité virtuelle, la cybersécurité et bien plus encore. Ne manquez pas l'opportunité de vous immerger dans l'avenir passionnant de la technologie.</p><p>Joignez-vous à nous pour des ateliers interactifs, des démonstrations en direct et des sessions de questions-réponses avec les innovateurs qui façonnent notre avenir numérique.</p><p>Réservez vos billets dès maintenant pour vivre une expérience inoubliable à l'événement <span class=important-text>Somaro Madagascar 2023</span>. Places limitées, réservez tôt pour ne pas manquer cette occasion unique !</p><p>Rejoignez-nous et soyez prêt à découvrir le futur dès aujourd'hui.</p></body></html>";
        String urlSomaro= "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQk6OsHFAECGW1s4oiOQR5kBzQB-7sSq7ljrBEoGSBn1Y9H93ZlaA7EH0tpqe5WR67HRUg&usqp=CAU";

        String htmlContentJeuxDestIles = "<!DOCTYPE html><html><head><style>body{font-family: Arial, sans-serif;font-size: 16px;line-height: 1.6;color: #333;}.title{font-size: 24px;font-weight: bold;color: #FF5733;}.details{font-size: 18px;color: #666;}.important{font-weight: bold;color: #007BFF;}</style></head><body><p class=title>Jeux des Îles Madagascar 2023</p><p class=details>Ne manquez pas les <span class=important>Jeux des Îles Madagascar 2023</span>, un événement sportif d'envergure régionale qui se tiendra à travers diverses villes de Madagascar. Ces jeux rassembleront des athlètes et des équipes de différentes îles de l'océan Indien pour des compétitions passionnantes.</p><p class=details>Points forts des Jeux :<ul><li>Compétitions variées dans une atmosphère festive</li><li><span class=important>Cérémonie d'ouverture</span> spectaculaire</li><li>Rencontres sportives entre les nations insulaires</li><li>Opportunités de découvrir la culture malgache</li></ul></p></body></html>";
        String urlJeuxDesIles= "\n" +
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Factu.orange.mg%2Fles-identites-de-la-11eme-edition-des-jeux-de-iles-de-locean-indien-devoilees%2F&psig=AOvVaw05fGss0RsQzP6BJVgIDnyL&ust=1691442641422000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCJiOw__4yIADFQAAAAAdAAAAABAE";

        this.Events = new ArrayList<>();
        Events.add(new Event("SOMARO 2023", "Nosy Be", "Festivale Musicale et culturelle", 3,htmlContentSomaro,urlSomaro));
        Events.add(new Event("Jeux des Iles", "Antananarivo", "Jeux", 4, htmlContentJeuxDestIles,urlJeuxDesIles));
        Events.add(new Event("Resort C", "City Z", "Beachfront resort", 5, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","https://farm9.staticflickr.com/8410/30193245810_7b7ff74cd5.jpg"));
        Events.add(new Event("Hotel A", "City X", "Luxurious hotel", 4, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event4"));
        Events.add(new Event("Guesthouse B", "City Y", "Cozy guesthouse", 3, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event1"));
        Events.add(new Event("Resort C", "City Z", "Beachfront resort", 5, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event2"));
        Events.add(new Event("Hotel A", "City X", "Luxurious hotel", 4, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event3"));
        Events.add(new Event("Guesthouse B", "City Y", "Cozy guesthouse", 3, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event4"));
        Events.add(new Event("Resort C", "City Z", "Beachfront resort", 5, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event1"));
        Events.add(new Event("Hotel A", "City X", "Luxurious hotel", 4, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event2"));
        Events.add(new Event("Guesthouse B", "City Y", "Cozy guesthouse", 3, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event3"));
        Events.add(new Event("Resort C", "City Z", "Beachfront resort" , 5, "<html><body><h1>Hello, <em>World</em>!</h1></body></html>","Event4"));

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new EventAdapter(Events);
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
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                return true;
            }
        });

        return view;
    }

    private void filterData(String query) {
        Log.d("tag= ", "filterData ********************************");
        if (filteredEvents.size() != 0) {
            filteredEvents.clear();
            Log.d("tag= ", "filteredEvents != null");
        }


        for (Event Event : Events) {
            if (Event.getNom().toLowerCase().contains(query.toLowerCase()) ||
                    Event.getLieu().toLowerCase().contains(query.toLowerCase())) {
                filteredEvents.add(Event);
            }
        }
        adapter= new EventAdapter(filteredEvents);
        adapter.notifyDataSetChanged();
    }
}