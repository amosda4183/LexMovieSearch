package com.example.atlbr.lexmoviesearch;

/* **********************************************************************
 * **********************************************************************
 * **********************************************************************
 * This application was developed by David Amos.
 * CIT 238 - Android Programming I - Spring 2018 Final Project
 *
 * This application will use an SQLite Database created within the DatabaseOperations
 * class, and allow the user to simulate a search for movies playing Lexington.
 *
 * The user will be able to search based on movie title, a specific theater, or date
 * and time they would like to view their movie.
 *
 * Each search activity will call the appropriate method from the Database class, and
 * also pass user input for the search. The result of the search will be returned by the
 * method called in that activity, and passed to the SearchResult activity to be displayed
 * for the user.
 *
 * Finally, the user will be able to navigate to a screen where they can choose
 * one of the four theaters included, and they will be taken to the concession
 * menu for that specific theater.
 *
 * **********************************************************************
 * **********************************************************************
 * **********************************************************************
 */

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup action bar with title and icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" Movie Search App");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.film_roll);

        //Create layout view references
        final Button titleSearch, theaterSearch,showtimeSearch,concessionChoice;

        //Link buttons to layout file
        titleSearch = findViewById(R.id.movieTitle);
        theaterSearch = findViewById(R.id.theater);
        showtimeSearch = findViewById(R.id.showtime);
        concessionChoice = findViewById(R.id.concessionButton);


        //Create onClick listeners and intents for each button to send user to the corresponding screen
        titleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent titleIntent = new Intent(getBaseContext(), TitleSearch.class);
                startActivity(titleIntent);

            }
        });

        theaterSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent theaterIntent = new Intent(getBaseContext(), TheaterSearch.class);
                startActivity(theaterIntent);

            }
        });

        showtimeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showtimeIntent = new Intent(getBaseContext(), ShowtimeSearch.class);
                startActivity(showtimeIntent);

            }
        });

        concessionChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent concessions = new Intent(getBaseContext(),ConcessionChoice.class);
                startActivity(concessions);
            }
        });
    }
}
