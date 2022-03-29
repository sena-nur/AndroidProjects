package com.sena.travelbook.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sena.travelbook.R;
import com.sena.travelbook.adapter.CustomAdapter;
import com.sena.travelbook.model.Place;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView ;
    CustomAdapter customAdapter;
    SQLiteDatabase database;
    ArrayList<Place> placeList = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater(); // herhangi bir layout u xml le bağlama xml dosyalarını menü objesine çevirme
        menuInflater.inflate(R.menu.add_location,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.add_location_item) {
            Intent intent = new Intent(MainActivity.this , MapsActivity.class);
            intent.putExtra("info", "new");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        getData();

    }

    public void getData() {
        customAdapter = new CustomAdapter(this , placeList);
        try{
            database = this.openOrCreateDatabase("Places", MODE_PRIVATE,null);
            Cursor cursor = database.rawQuery("SELECT * FROM places " , null);
            int nameIx = cursor.getColumnIndex("name");
            int latitudeIx = cursor.getColumnIndex("latitude");
            int longitudeIx = cursor.getColumnIndex("longitude");
            while (cursor.moveToNext()) {
               String nameFromDataBase = cursor.getString(nameIx);
               String latitudeFromDataBase = cursor.getString(latitudeIx);
               String longitudeFromDataBase = cursor.getString(longitudeIx);

               Double latitude = Double.parseDouble(latitudeFromDataBase);
               Double longitude = Double.parseDouble(longitudeFromDataBase);

               Place place = new Place (nameFromDataBase , latitude , longitude);
               placeList.add(place);
            }
            customAdapter.notifyDataSetChanged();
            cursor.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this , MapsActivity.class);
                intent.putExtra("info", "old");
                intent.putExtra("place" , placeList.get(position));
                startActivity(intent);
            }
        });



    }


}