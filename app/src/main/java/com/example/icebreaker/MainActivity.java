package com.example.icebreaker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Dumb way for splash screen, replace with actual splash later
//        setContentView(R.layout.splash_screen);
//        new CountDownTimer(3000, 1000) {
//            public void onTick(long millisUntilFinished) {
//            }
//
//            public void onFinish() {
//                setContentView(R.layout.home);
//            }
//        }.start();
        setContentView(R.layout.home);
        Toolbar toolbar = findViewById(R.id.HomeToolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
    }

    public void w_clicked(View view) {
        Intent intent = new Intent(MainActivity.this, WActivity.class);
        startActivity(intent);
    }

    public void or_clicked(View view) {
        Intent intent = new Intent(MainActivity.this, OrActivity.class);
        startActivity(intent);
    }

    public void if_clicked(View view) {
        Intent intent = new Intent(MainActivity.this, IfActivity.class);
        startActivity(intent);
    }

    public void game_clicked(View view) {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }
}
