package fr.iavotiana.travel.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import fr.iavotiana.travel.R;
import fr.iavotiana.travel.model.Destination;

public class ListDestination extends ArrayAdapter<Destination> {

    public ListDestination(Context context, ArrayList<Destination> listDestinations){
        super(context, R.layout.list_item,listDestinations);
    }

    @NotNull
    @Override
    public View getView(int position, @Nullable View convertView, @NotNull ViewGroup parent){

        Destination destination = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ImageView imageList= convertView.findViewById(R.id.imgList);
        TextView title= convertView.findViewById(R.id.title);
        TextView detail= convertView.findViewById(R.id.detail);
        TextView favori= convertView.findViewById(R.id.favori);

        imageList.setImageResource(destination.imageId);
        title.setText(destination.title);
        detail.setText(destination.description);
        favori.setText(destination.note);

        imageList.setImageResource(destination.imageId);
        title.setText(destination.title);
        detail.setText(destination.description);
        favori.setText(destination.note);

        return super.getView(position,convertView,parent);
    }
}
