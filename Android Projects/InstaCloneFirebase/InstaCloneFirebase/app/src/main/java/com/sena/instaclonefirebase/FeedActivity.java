package com.sena.instaclonefirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<String> userEmailFromFB ;
    ArrayList<String> userCommentFromFB;
    ArrayList<String > userImageFromFB;
    FeedRecyclerAdapter feedRecyclerAdapter;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater  = getMenuInflater();
        inflater.inflate(R.menu.insta_options_menu , menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.add_post) {
            Intent intentToUpload = new Intent(getApplicationContext() , UploadActivity.class);
            startActivity(intentToUpload);

        }
        else if (item.getItemId() == R.id.signout) {
            firebaseAuth.signOut();
            Intent intentToSignUp = new Intent(getApplicationContext() , SignUpActivity.class);
            startActivity(intentToSignUp);
            finish();



        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userCommentFromFB = new ArrayList<>();
        userEmailFromFB = new ArrayList<>();
        userImageFromFB = new ArrayList<>();

        getDataFromFirestore();

        //RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this) );
        feedRecyclerAdapter = new FeedRecyclerAdapter(userEmailFromFB , userCommentFromFB , userImageFromFB);
        recyclerView.setAdapter(feedRecyclerAdapter);
    }

    public void getDataFromFirestore () { //Data okuma
        CollectionReference collectionReference = firebaseFirestore.collection("Posts");
        // tarihe göre azalarak sıralama
        collectionReference.orderBy("date" , Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null) {
                    Toast.makeText(getApplicationContext() , error.getLocalizedMessage().toString() , Toast.LENGTH_LONG).show();
                }
                if(value != null) {
                    for(DocumentSnapshot snapshot : value.getDocuments()) {
                        Map<String, Object > data = snapshot.getData();
                        String comment = (String) data.get("comment"); //Casting
                        String userEmail =(String) data.get("useremail"); //Casting
                        String downloadUrl =(String) data.get("downloadurl");

                        //System.out.println(comment);

                        userCommentFromFB.add(comment);
                        userEmailFromFB.add(userEmail);
                        userImageFromFB.add(downloadUrl);

                        feedRecyclerAdapter.notifyDataSetChanged();// adapteri içeri yeni veri geldi diye uyarıyoruz
                    }
                }

            }
        });
    }
}