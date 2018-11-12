package com.example.atlbr.lexmoviesearch;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


//This activity will simply display the results of the search query performed
//in other activities. It will receive and intent from the previous activity as well
//as string data which it will translate into its textview
public class SearchResults extends AppCompatActivity {

    String output = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        //Setup action bar with title and icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" Search Results");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.film_roll);

        TextView searchResult = findViewById(R.id.searchResult);

        //Receive intent from whichever search activity is being used
        Intent intent = getIntent();

        //Receive data via intent from search activity and print it
        //If query does not match any table data, print a message letting
        //the user know this.
        output = intent.getStringExtra("result");

        if(output.length() != 0)
            searchResult.setText(output);
        else
            searchResult.setText("NO RESULTS FOUND!");

    }
}
