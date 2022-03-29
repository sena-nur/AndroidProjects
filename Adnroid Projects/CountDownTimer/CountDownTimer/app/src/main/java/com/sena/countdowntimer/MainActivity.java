package com.sena.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("Left: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Done!",Toast.LENGTH_LONG).show();
                textView.setText("Finished!");

            }
        }.start();

    }
}