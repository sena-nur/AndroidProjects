package com.sena.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView [] imageArray;
    Handler handler;
    Runnable runnable;
    int clicked=0;
    int score ;
    int number=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageArray = new ImageView[] { imageView,  imageView2,  imageView3,  imageView4,  imageView5,  imageView6, imageView7,  imageView8,  imageView9};
        hideImage();
        score =0;

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView1.setText("Time : "+millisUntilFinished/1000);
                number--;

            }

            @Override
            public void onFinish() {
                textView1.setText("time is over !");
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart ?");
                alert.setMessage("Are you sure to restart game ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // restart
                        Intent intent = getIntent();
                        finish(); //güncel aktiviteyi bitirecek
                        startActivity(intent); // aynı aktiviteyi baştan başlatacak

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this , "Game Over" , Toast.LENGTH_SHORT).show();

                    }
                });
                alert.show();



            }
        }.start();
    }
   public void increaseScore (View view) {
        if(clicked==0) {
            score++;
            textView2.setText("Score : " + score);
            clicked=1;
        }


   }

   public void hideImage () {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random ();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                clicked=0;
                handler.postDelayed(this , 1500);

            }
        };
        handler.post(runnable);

   }



}