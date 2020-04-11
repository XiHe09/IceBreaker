package com.example.icebreaker;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.navigation.NavigationView;

public class WActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w);
        Toolbar toolbar = findViewById(R.id.WToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();
        if (id == R.id.drawer_home) {
            Intent intent = new Intent(WActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.drawer_or) {
            Intent intent = new Intent(WActivity.this, OrActivity.class);
            startActivity(intent);
        } else if (id == R.id.drawer_w) {
            Intent intent = new Intent(WActivity.this, WActivity.class);
            startActivity(intent);
        } else if (id == R.id.drawer_game) {
            Intent intent = new Intent(WActivity.this, GameActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void what_clicked(View view) {
        Intent intent = new Intent(WActivity.this, WSubActivity.class);
        intent.putExtra(getString(R.string.w_intent_key),"WHAT");
        startActivity(intent);
    }

    public void who_clicked(View view) {
        Intent intent = new Intent(WActivity.this, WSubActivity.class);
        intent.putExtra(getString(R.string.w_intent_key),"WHO");
        startActivity(intent);
    }

    public void when_clicked(View view) {
        Intent intent = new Intent(WActivity.this, WSubActivity.class);
        intent.putExtra(getString(R.string.w_intent_key),"WHEN");
        startActivity(intent);
    }

    public void why_clicked(View view) {
        Intent intent = new Intent(WActivity.this, WSubActivity.class);
        intent.putExtra(getString(R.string.w_intent_key),"WHY");
        startActivity(intent);
    }

    public void where_clicked(View view) {
        Intent intent = new Intent(WActivity.this, WSubActivity.class);
        intent.putExtra(getString(R.string.w_intent_key),"WHERE");
        startActivity(intent);
    }
}
