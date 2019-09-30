package com.example.Favorite_country;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView flag; // 국기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flag = findViewById(R.id.flag);

        // Glide
        Glide.with(MainActivity.this).load(R.drawable.ic_launcher_background).into(flag);
    }
}
