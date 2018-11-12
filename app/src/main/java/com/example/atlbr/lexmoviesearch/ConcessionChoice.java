package com.example.atlbr.lexmoviesearch;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//This activity will be a bridge between the mainActivity and the concession menus
//Three buttons on the screen will allow the user to choose a theater and
//view that concession menu. Each menu is a basic representation of some sample items
public class ConcessionChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.concession_choice);

        //Setup action bar with title and icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" Concession Options");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.film_roll);

        //Create button references
        final Button cinemarkButton,regalButton,stateButton,tavernButton;

        //Link references to layout file
        cinemarkButton = findViewById(R.id.cinemarkMenu);
        regalButton= findViewById(R.id.regalMenu);
        tavernButton= findViewById(R.id.tavernMenu);


        //Create listeners for buttons to create menu for each option and send user to the menu
        cinemarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemarkMenu = new Intent(getBaseContext(),CinemarkMenu.class);
                startActivity(cinemarkMenu);
            }
        });

        regalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regalMenu = new Intent(getBaseContext(),RegalMenu.class);
                startActivity(regalMenu);
            }
        });

        tavernButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tavernMenu= new Intent(getBaseContext(),TavernMenu.class);
                startActivity(tavernMenu);
            }
        });

    }
}
