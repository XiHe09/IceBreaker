package com.example.icebreaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import java.util.Arrays;
import java.util.Collections;
        import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class WRandom extends AppCompatActivity {
    public ArrayList<WItem> wItemList;
    public int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questioncards);
        Toolbar toolbar = findViewById(R.id.WToolbar2);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        createArrayList();
        //showNext(this.getCurrentFocus());
    }

    public void createArrayList() {
        wItemList = new ArrayList<>();
        createStringArrays(R.array.what_list);
        createStringArrays(R.array.where_list);
        createStringArrays(R.array.who_list);
        createStringArrays(R.array.when_list);
        createStringArrays(R.array.why_list);
        size = wItemList.size();
        for (WItem w : wItemList) {
            System.out.println(w.getQuestion());
        }
    }

    public void showNext(View view) {
        boolean done = true;
        TextView tv = (TextView) findViewById(R.id.textView2);
        if (size == 0) {
            tv.setText(R.string.ALLDONE);
        }
        while (done && size > 0) {
            final int random = new Random().nextInt(size);
            if (!wItemList.get(random).isDone()) {
                tv.setText(wItemList.get(random).getQuestion());
                wItemList.remove(random);
                size--;
                done = false;
            }
        }
    }
    public void createStringArrays(int res_id) {
        for (String question : getResources().getStringArray(res_id)) {
            wItemList.add(new WItem(false, question));
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
        createArrayList();
        return super.onOptionsItemSelected(item);
    }

}