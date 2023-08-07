package fr.iavotiana.travel.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fr.iavotiana.travel.R;


public class AcceuilFragment extends Fragment {

    private VideoView videoView;
    private NotificationManagerCompat notificationManagerCompat;
    private NotificationCompat.Builder builder;
    private static final String CHANNEL_ID = "myChannel";

    public AcceuilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_acceuil, container, false);

        TextView toolbarTitle = view.findViewById(R.id.toolbarTitle);



        videoView = view.findViewById(R.id.video);

        // Replace "your_video_name" with the name of the video resource in the 'res/raw' folder
        String videoPath = "android.resource://" + requireContext().getPackageName() + "/raw/video";
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);

        MediaController mediaController = new MediaController(requireContext());
        mediaController.setAnchorView(videoView);
       // videoView.setMediaController(mediaController);

        Button destinationButton = view.findViewById(R.id.btn_destination);
        destinationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DestinationFragment destinationFragment = new DestinationFragment();
                switchFragment(destinationFragment);
            }
        });

        Button hebergementButton = view.findViewById(R.id.btn_hebergement);
        hebergementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HebergementFragment hebergementFragment = new HebergementFragment();
                switchFragment(hebergementFragment);
            }
        });

        Button eventButton = view.findViewById(R.id.btn_event);
        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventFragment eventFragment = new EventFragment();
                switchFragment(eventFragment);
            }
        });

        Button inscriptionButton = view.findViewById(R.id.btn_inscription);
        inscriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InscriptionFragment inscriptionFragment = new InscriptionFragment();
                switchFragment(inscriptionFragment);
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = requireContext().getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

        builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)  // Set a valid small icon
                .setContentTitle("TRAVEL MADAGASCAR")
                .setContentText("Félicitations !\n" +
                        "Vous êtes maintenant abonné à nos mises à jour.\n" +
                        "Restez à jour avec les dernières nouvelles, offres spéciales et événements passionnants.");

        notificationManagerCompat = NotificationManagerCompat.from(requireContext());

        Button sendNotificationButton = view.findViewById(R.id.send_notification_button);
        sendNotificationButton.setOnClickListener(v -> sendNotification());
    }

    private void sendNotification() {
        notificationManagerCompat.notify(123, builder.build());
    }

    @Override
    public void onStart() {
        super.onStart();
        videoView.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        videoView.stopPlayback();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        videoView.stopPlayback(); // Stop the video when the Fragment's view is destroyed
    }

    public void switchFragment(Fragment secondFragment){
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, secondFragment);
        transaction.addToBackStack(null); // Add to back stack for back navigation
        transaction.commit();
    }


}