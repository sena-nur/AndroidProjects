package com.sena.simpsonbook;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Simpson> {
    private ArrayList<Simpson> simpsonArrayList;
    private Activity context;
    public CustomAdapter(ArrayList<Simpson> simpsonArrayList , Activity context){
        super(context,R.layout.costum_view, simpsonArrayList);
        this.simpsonArrayList= simpsonArrayList;
        this.context= context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View customView = layoutInflater.inflate(R.layout.costum_view, null , true);
        TextView nameView = customView.findViewById(R.id.customTextView);
        nameView.setText(simpsonArrayList.get(position).getName());
        return customView;
    }
}
