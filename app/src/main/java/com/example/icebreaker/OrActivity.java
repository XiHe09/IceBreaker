package com.example.icebreaker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView RecyclerView;
    private OrAdapter Adapter;
    private RecyclerView.LayoutManager LayoutManager;
    public static ArrayList<WItem> OrItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_or);
        Toolbar toolbar = findViewById(R.id.OrToolbar);
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
        populateList();
        buildRecyclerView();
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
            for (WItem w : OrItemList) {
                w.setDone(false);
            }
            clear();
        }
        return super.onOptionsItemSelected(item);
    }

    public void clear() {
        RecyclerView mListView = (RecyclerView) findViewById(R.id.Or_list_rview);
        for (int i = 0; i < mListView.getChildCount(); i++) {
            View mChild = mListView.getChildAt(i);
            Button button = (Button) mChild.findViewById(R.id.or_row_middle);
            button.setTextColor(Color.parseColor("#26547C"));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();
        if (id == R.id.drawer_home) {
            Intent intent = new Intent(OrActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.drawer_or) {
            Intent intent = new Intent(OrActivity.this, OrActivity.class);
            startActivity(intent);
        } else if (id == R.id.drawer_w) {
            Intent intent = new Intent(OrActivity.this, WActivity.class);
            startActivity(intent);
        } else if (id == R.id.drawer_game) {
            Intent intent = new Intent(OrActivity.this, GameActivity.class);
            startActivity(intent);
        } else if (id == R.id.drawer_if) {
            Intent intent = new Intent(OrActivity.this, IfActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void orRandomClick(View view) {
        Intent intent = new Intent(OrActivity.this, OrRandom.class);
        startActivity(intent);
    }

    public void populateList() {
        OrItemList = new ArrayList<>();
        for (String question: getResources().getStringArray(R.array.Or_list)) {
            OrItemList.add(new WItem(false, question));
            System.out.println("Added item: "+question);
        }
        Collections.sort(OrItemList, new Comparator<WItem>() {
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

    public void buildRecyclerView() {
        RecyclerView = findViewById(R.id.Or_list_rview);
        RecyclerView.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        Adapter = new OrAdapter(OrItemList);
        RecyclerView.setLayoutManager(LayoutManager);
        RecyclerView.setAdapter(Adapter);

        Adapter.setOnItemClickListener(new OrAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // TODO?
            }
        });
    }
}
