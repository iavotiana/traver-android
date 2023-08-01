package fr.iavotiana.travel.view;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.Nullable;

import fr.iavotiana.travel.R;


public class AcceuilFragment extends Fragment {

    private VideoView videoView;


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

        videoView = view.findViewById(R.id.video);

        // Replace "your_video_name" with the name of the video resource in the 'res/raw' folder
        String videoPath = "android.resource://" + requireContext().getPackageName() + "/raw/video";
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);

        MediaController mediaController = new MediaController(requireContext());
        mediaController.setAnchorView(videoView);
       // videoView.setMediaController(mediaController);

        return view;
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
}