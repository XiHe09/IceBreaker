package com.example.icebreaker;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class OrRandom extends AppCompatActivity {
    public ArrayList<WItem> orItemList;
    public int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orquestioncards);
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
        orItemList = new ArrayList<>();
        createStringArrays(R.array.Or_list);
        size = orItemList.size();
    }

    public void showNext(View view) {
        boolean done = true;
        TextView tv = (TextView) findViewById(R.id.textView2);
        if (size == 0) {
            tv.setText(R.string.ALLDONE);
        }
        while (done && size > 0) {
            final int random = new Random().nextInt(size);
            if (!orItemList.get(random).isDone()) {
                tv.setText(orItemList.get(random).getQuestion());
                orItemList.remove(random);
                size--;
                done = false;
            }
        }
    }
    public void createStringArrays(int res_id) {
        for (String question : getResources().getStringArray(res_id)) {
            orItemList.add(new WItem(false, question));
        }
        Collections.sort(orItemList, new Comparator<WItem>() {
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
