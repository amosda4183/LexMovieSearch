package com.example.atlbr.lexmoviesearch;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TheaterSearch extends AppCompatActivity {

    String userInput ="";

    //Create layout view references
    EditText theaterSearch;
    Button startSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theater_search);

        //Setup action bar with title and icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" Search By Theater");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.film_roll);


        //Instantiate database
        final  Database db;
        db = new Database(this);

        //Link references to layout file
        theaterSearch = findViewById(R.id.theaterSearch);
        startSearch = findViewById(R.id.startSearch);

        //Create listener for startSearch button
        startSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Check that user entered data into the search field
                if(theaterSearch.getText().toString().length() == 0)
                    Toast.makeText(TheaterSearch.this, "You must enter a theater name!", Toast.LENGTH_SHORT).show();

                else{

                    //Read input from editText field and translate it into a search query
                    userInput = theaterSearch.getText().toString();
                    String result = db.getTheaterSearchResult(userInput);

                    //Send user to search results activity
                    Intent searchResult = new Intent(getBaseContext(), SearchResults.class);

                    searchResult.putExtra("result", result);
                    startActivity(searchResult);
                }

            }
        });
    }
}
