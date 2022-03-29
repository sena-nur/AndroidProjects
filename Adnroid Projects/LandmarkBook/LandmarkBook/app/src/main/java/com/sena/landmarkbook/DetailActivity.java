package com.sena.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

//import static com.sena.landmarkbook.MainActivity.selectedImages;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView imageView = findViewById(R.id.imageView);
        TextView  landmarNameText = findViewById(R.id.LandmarkNameText);
        TextView countryNameText = findViewById(R.id.CountryNameText);
        Intent intent = getIntent();
        String landmarkNames= intent.getStringExtra("name");
        String countryNames = intent.getStringExtra("countryname") ;
        landmarNameText.setText(landmarkNames);
        countryNameText.setText(countryNames);
        //imageView.setImageBitmap(selectedImages);
        Singleton singleton = Singleton.getInstance();
        imageView.setImageBitmap(singleton.getSelectedImage());
    }
}