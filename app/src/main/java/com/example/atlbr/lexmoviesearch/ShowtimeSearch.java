package com.example.atlbr.lexmoviesearch;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ShowtimeSearch extends AppCompatActivity {

    //Declare needed variables
    private String timeChosen, dateChosen;
    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    private int hour, minute, year, month, day;
    private Calendar calendar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showtime_search);

        //Setup action bar with title and icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" Search By Showtime");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.film_roll);

        //Create layout view references
        final Button selectTime,selectDate,startSearch;

        //Link buttons to layout file
        selectTime = findViewById(R.id.selectTime);
        selectDate = findViewById(R.id.selectDate);
        startSearch = findViewById(R.id.startSearch);

        //Instantiate database
        final  Database db;
        db = new Database(this);


        //Create listeners for buttons
        //Create listener for button press to go to time picker dialog
        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Setup time picker dialog
                timePickerDialog = new TimePickerDialog(ShowtimeSearch.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                //Save chosen time to string variable for output later
                                timeChosen = hour + ":" + minute;
                            }
                        },hour,minute,true);

                //show time picker dialog
                timePickerDialog.show();
            }
        });

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                //setup date picker dialog
                datePickerDialog = new DatePickerDialog(ShowtimeSearch.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                //Save chosen date to string variable
                                dateChosen = (month+1) + "/" + day + "/" + year;
                            }
                        },year,month,day);
                //Show date picker dialog
                datePickerDialog.show();
            }
        });

        //Create listener for startSearch button
        startSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Check for values inside dateChosen and timeChosen
                //User will receive a toast to choose values if they have not done so
                if(dateChosen == null){
                    Toast.makeText(ShowtimeSearch.this, "You must select a date to search!", Toast.LENGTH_SHORT).show();
                }
                else if(timeChosen == null){
                    Toast.makeText(ShowtimeSearch.this, "You must select a time to search!", Toast.LENGTH_SHORT).show();
                }

                //This method passes the time and date the user chooses, and returns the table
                //entries matching the date and time.
                //This data is then sent to the SearchResults activity and displayed

                else {
                    String result = db.getShowtimeSearchResult(dateChosen, timeChosen);

                    //Send user to search results activity with data from search
                    Intent searchResult = new Intent(getBaseContext(), SearchResults.class);
                    searchResult.putExtra("result", result);
                    startActivity(searchResult);
                }
            }
        });

    }
}
