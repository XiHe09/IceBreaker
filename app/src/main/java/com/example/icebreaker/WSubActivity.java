package com.example.icebreaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class WSubActivity extends AppCompatActivity {
        //implements NavigationView.OnNavigationItemSelectedListener{
    private RecyclerView RecyclerView;
    private WAdapter Adapter;
    private RecyclerView.LayoutManager LayoutManager;
    public static ArrayList<WItem> wItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w_sub);
        Toolbar toolbar = findViewById(R.id.WToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
        // switch between different W based on intent
        Button title_button = findViewById(R.id.wTitleButton);
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            // TODO iteration 2: go random
        } else {
            String type = extras.getString(getString(R.string.w_intent_key));
            switch(type) {
                case "WHO" :
                    title_button.setText("WHO");
                    populateList(R.array.who_list);
                    break;
                case "WHEN" :
                    title_button.setText("WHEN");
                    populateList(R.array.when_list);
                    break;
                case "WHY" :
                    title_button.setText("WHY");
                    populateList(R.array.why_list);
                    break;
                case "WHAT" :
                    title_button.setText("WHAT");
                    populateList(R.array.what_list);
                    break;
                case "WHERE" :
                    title_button.setText("WHERE");
                    populateList(R.array.where_list);
                    break;

                default :
            }
        }
        buildRecyclerView();
    }

    // TODO iteration 2: current dummy populate w list from data in resources, replace with database later
    public void populateList(int res_id) {
        wItemList = new ArrayList<>();
        for (String question: getResources().getStringArray(res_id)) {
            wItemList.add(new WItem(false, question));
            System.out.println("Added item: "+question);
        }
        Collections.sort(wItemList, new Comparator<WItem>() {
            @Override
            public int compare(WItem o1, WItem o2) {
                if (o1.isDone() == o2.isDone()) {
                    return 0;
                } else if (o1.isDone()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
    }

    // create clear action button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.clear_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle clear button activities
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.clear_button) {
            for (WItem witem: wItemList) {
                witem.setDone(false);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void buildRecyclerView() {
        RecyclerView = findViewById(R.id.w_list_rview);
        RecyclerView.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        Adapter = new WAdapter(wItemList);
        RecyclerView.setLayoutManager(LayoutManager);
        RecyclerView.setAdapter(Adapter);

        Adapter.setOnItemClickListener(new WAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // TODO?
            }
        });
    }
/*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();
        if (id == R.id.drawer_home) {
            Intent intent = new Intent(WSubActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.drawer_or) {
            Intent intent = new Intent(WSubActivity.this, OrActivity.class);
            startActivity(intent);
        } else if (id == R.id.drawer_w) {
            Intent intent = new Intent(WSubActivity.this, WActivity.class);
            startActivity(intent);
        } else if (id == R.id.drawer_game) {
            Intent intent = new Intent(WSubActivity.this, GameActivity.class);
            startActivity(intent);
        } else if (id == R.id.if_title) {
            Intent intent = new Intent(WSubActivity.this, IfActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
}
