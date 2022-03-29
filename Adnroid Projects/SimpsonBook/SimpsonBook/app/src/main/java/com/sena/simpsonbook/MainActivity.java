package com.sena.simpsonbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.ListView);

        Simpson homer = new Simpson("Honer Simpson" , "Nuclear", R.drawable.homersimpson );
        Simpson lisa = new Simpson("Lisa Simpson" ,"Student" , R.drawable.lisasimpson);
        Simpson bart = new Simpson("Bart Simpson" , "Student", R.drawable.bartsimpson);

        ArrayList<Simpson> simpsons = new ArrayList<>();
        simpsons.add(homer);
        simpsons.add(lisa);
        simpsons.add(bart);

        //kendi adaptörümüzü yazıyoruz

        CustomAdapter customAdapter = new CustomAdapter(simpsons , MainActivity.this);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
                intent.putExtra("selectedSimpson", simpsons.get(position));  // Simpsonu serileştirerek yollama
                startActivity(intent);
            }
        });



    }
}