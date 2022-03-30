package com.sena.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //static Bitmap selectedImages ; //static her yerden ulaşılabilir

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.ListView);

        //Data
        ArrayList<String> landmarkNames = new ArrayList<>();
        landmarkNames.add("Pisa");
        landmarkNames.add("Eiffel");
        landmarkNames.add("Colesseo");
        landmarkNames.add("London Bridge");

        ArrayList<String> countryNames = new ArrayList<>();
        countryNames.add("Italy");
        countryNames.add("France");
        countryNames.add("Italy");
        countryNames.add("United Kingdom");

        Bitmap pisa = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.pisa);
        Bitmap eiffel = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.eiffel);
        Bitmap colesseo = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.colosseo);
        Bitmap londonBridge = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.bridge);
        ArrayList<Bitmap> landmarkImages = new ArrayList<>();
        landmarkImages.add(pisa);
        landmarkImages.add(eiffel);
        landmarkImages.add(colesseo);
        landmarkImages.add(londonBridge);



        //ListView
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, landmarkNames);
        listView.setAdapter(arrayAdapter); //lisview la arraylisti birbirini bağladık
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("name", landmarkNames.get(position));
                intent.putExtra("countryname" , countryNames.get(position));
                //selectedImages = landmarkImages.get(position);
                Singleton singleton = Singleton.getInstance();
                singleton.setSelectedImage(landmarkImages.get(position));
                startActivity(intent);

            }
        });
    }
}