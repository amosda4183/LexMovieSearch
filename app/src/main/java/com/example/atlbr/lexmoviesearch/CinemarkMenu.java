package com.example.atlbr.lexmoviesearch;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//Display Cinemark concession menu
public class CinemarkMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cinemark_menu);

        //Setup action bar with title and icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" Cinemark Lexington");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.film_roll);
    }
}
