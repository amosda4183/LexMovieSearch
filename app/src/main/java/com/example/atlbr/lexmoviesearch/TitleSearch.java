package com.example.atlbr.lexmoviesearch;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TitleSearch extends AppCompatActivity {

    String userInput, result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_search);


        //Setup action bar with title and icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" Search By Title");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.film_roll);

        //Create layout view references
        final EditText titleSearch;
        final Button startSearch;

        //Link references to layout file
        titleSearch = findViewById(R.id.titleSearch);
        startSearch = findViewById(R.id.startSearch);

        //Instantiate database
        final  Database db;
        db = new Database(this);

        //Create listener for startSearch button
        startSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check that user entered a value for the search
                if(titleSearch.getText().toString().length() == 0)
                    Toast.makeText(TitleSearch.this, "You must enter a movie title!", Toast.LENGTH_SHORT).show();

                    //Read input from editText field and translate it into a search query
                else {
                    userInput = titleSearch.getText().toString();

                    result = db.getMovieSearchResult(userInput);


                    //Send user to search results activity
                    Intent searchResult = new Intent(getBaseContext(), SearchResults.class);

                    searchResult.putExtra("result", result);
                    startActivity(searchResult);
                }
            }
        });
    }


}
