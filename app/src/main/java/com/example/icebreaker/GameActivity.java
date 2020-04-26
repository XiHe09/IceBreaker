package com.example.icebreaker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class GameActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        Toolbar toolbar = findViewById(R.id.GToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        createWebView();
    }

    public void createWebView() {
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://playtictactoe.org/");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null && (url.startsWith("http://") || url.startsWith("https://"))) {
                    view.getContext().startActivity(
                            new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else {
                    return false;
                }
            }
        });
        Button online = findViewById(R.id.onlineButton);
        Button inperson = findViewById(R.id.inPersonButton);
        online.setBackgroundColor(getResources().getColor(R.color.b12));
        inperson.setBackgroundColor(getResources().getColor(R.color.b11));
    }

    public void createWebView2() {
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://creativetechguy.com/utilities/randomtimer");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null && (url.startsWith("http://") || url.startsWith("https://"))) {
                    view.getContext().startActivity(
                            new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else {
                    return false;
                }
            }

            //InputStream is = getApplicationContext().getAssets().open()
            //Reader r = new InputStreamReader(is);
            //String details = Utils.readertoString(r);
            //details = details.replace("%product_name%",productName );
        });
        Button online = findViewById(R.id.onlineButton);
        Button inperson = findViewById(R.id.inPersonButton);
        online.setBackgroundColor(getResources().getColor(R.color.b11));
        inperson.setBackgroundColor(getResources().getColor(R.color.b12));

    }

    public void inPersonClicked(View view) {
        createWebView2();

    }

    public void onlineClicked(View view) {
        createWebView();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();
        if (id == R.id.drawer_home) {
            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.drawer_or) {
            Intent intent = new Intent(GameActivity.this, OrActivity.class);
            startActivity(intent);
        } else if (id == R.id.drawer_w) {
            Intent intent = new Intent(GameActivity.this, WActivity.class);
            startActivity(intent);
        } else if (id == R.id.drawer_game) {
            Intent intent = new Intent(GameActivity.this, GameActivity.class);
            startActivity(intent);
        } else if (id == R.id.drawer_if) {
            Intent intent = new Intent(GameActivity.this, IfActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
