package com.example.atlbr.lexmoviesearch;

import android.provider.BaseColumns;

//This class will create the base columns for the database and its tables by implementing
//the BaseColumn interface. This class will be called upon during creation of the database

public class TableData implements BaseColumns {

    //Constructor
    public TableData(){}

    public static final String DATABASE_NAME = "Lexington_Movies";
    public static final String THEATER_NAME = "Theater_Name";

    public static final String MOVIE_ID = "Movie_ID";
    public static final String THEATER_ID = "Theater_ID";
    public static final String MOVIE_NAME = "Movie_Name";





}
