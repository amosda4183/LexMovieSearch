package com.example.atlbr.lexmoviesearch;

/* **********************************************************************
/************************************************************************
/************************************************************************
*
* This java class will utilize the SQLiteOpenHelper class to create the database
* and tables used by the application when the user searches for a movie, theater,
* or showtime.
*
* The data tables will be created and filled inside this class, and will create
* an instance of the same database when a new Database object is created in any
* other activity. This will ensure data is consistent across the application.
*
* This class will also include methods used in the different search activities.
* These functions will use the rawQuery function and comparisons to return data from the
* database that matches the user's search criteria
*
* ************************************************************************
* ************************************************************************
* ************************************************************************ */



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

    //Variables to simplify the constructor
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MovieDatabase.db";

    //Create variables for table and column names

    //These are the table names
    public static final String  MOVIE_TABLE_NAME = "Movie_Table";
    public static final String  THEATER_TABLE_NAME = "Theater_Table";
    public static final String  SHOWTIME_TABLE_NAME = "Showtime_Table";

    //Column names for the tables
    public static final String MOVIE_TITLE_COL = "Movie_Title";
    public static final String MOVIE_ID_COL = "Movie_ID";


    public static final String THEATER_NAME_COL = "Theater_Name";
    public static final String THEATER_ID_COL = "Theater_ID";

    public static final String DATE_COL = "Date";
    public static final String TIME_COL = "Time";



    //Default constructor for the database
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database Operations","Database Created...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create the tables in the database
        //Movie Table:
            db.execSQL("CREATE TABLE " + MOVIE_TABLE_NAME + " ( " +
                       MOVIE_TITLE_COL + " VARCHAR, " +
                       MOVIE_ID_COL + " INTEGER NOT NULL PRIMARY KEY);");
            Log.d("Database Operations","Movie Table Created...");

        //Theater Table:
            db.execSQL("CREATE TABLE " + THEATER_TABLE_NAME + "(" +
                        THEATER_NAME_COL + " VARCHAR, "+
                        THEATER_ID_COL + " INTEGER NOT NULL PRIMARY KEY);");
        Log.d("Database Operations","Theater Table Created...");


        //Showtimes Table:
            db.execSQL("CREATE TABLE " + SHOWTIME_TABLE_NAME + "(" +
            DATE_COL + " VARCHAR, " +
            TIME_COL + " VARCHAR," +
            MOVIE_TITLE_COL + " VARCHAR," +
            THEATER_NAME_COL + " VARCHAR );");
        Log.d("Database Operations","Showtime Table Created...");


        //Load values into the tables using ContentValues objects and the put method

        //Populate movie_table
        ContentValues movie_values = new ContentValues();
        movie_values.put(MOVIE_TITLE_COL,"Avengers");
        movie_values.put(MOVIE_ID_COL,0);
        db.insert(MOVIE_TABLE_NAME,null,movie_values);
        Log.d("Database Operations","Movie Row Inserted...");


        //Populate theater_table
        ContentValues theater_values = new ContentValues();
        theater_values.put(THEATER_NAME_COL,"Cinemark");
        theater_values.put(THEATER_ID_COL,0);
        db.insert(THEATER_TABLE_NAME,null,theater_values);
        Log.d("Database Operations","Theater Row Inserted...");



        ContentValues showtimes_values = new ContentValues();
        showtimes_values.put(DATE_COL,"5/3/2018");
        showtimes_values.put(TIME_COL,"4:15");
        showtimes_values.put(THEATER_NAME_COL,"Cinemark Theater");
        showtimes_values.put(MOVIE_TITLE_COL,"Black Panther");
        db.insert(SHOWTIME_TABLE_NAME,null,showtimes_values);
        Log.d("Database Operations","Showtime Row Inserted...");

        }

    /* This method receives a string argument, which will be the movie title.
     * It then searches the Showtime table for entries matching the title, and outputs all results found
     * It is currently set to return the single entry in the table.
     * To iterate through a full table of data, I would implement a do while loop which would
     * use the cursor.moveToNext() function as the while condition.
     *
     */

        public String getMovieSearchResult(String search)
        {
            SQLiteDatabase db = this.getWritableDatabase();

            String result = "";

            //String array to represent the columns of the table in the query function
            String[] columns = {DATE_COL,TIME_COL, THEATER_NAME_COL, MOVIE_TITLE_COL};

            //cursor object will use a query to search the showtime table, in the movie title column,
            //for a string that matches the user
            Cursor cursor = db.query(SHOWTIME_TABLE_NAME,columns,
                    MOVIE_TITLE_COL+"= '"+(search)+"'" ,
                    null,null,null,null);


            while(cursor.moveToNext()){

                //indices is initialized using the cursor and the getColumnIndex function to return the
                //index of the corresponding columns passed to getColumnIndex
                int index0 = cursor.getColumnIndex(DATE_COL);
                int index1 = cursor.getColumnIndex(TIME_COL);
                int index2 = cursor.getColumnIndex(THEATER_NAME_COL);
                int index3 = cursor.getColumnIndex(MOVIE_TITLE_COL);

                //Using index, retrieve information from the entry where the search matches through
                //the cursor object. Save the entry to a string variable for output.
                String date = cursor.getString(index0);
                String time = cursor.getString(index1);
                String theater = cursor.getString(index2);
                String title = cursor.getString(index3);


                //Generate search result string, which contains the information retrieved from the table entry
                result = ("\nMovie: "+ title +
                          "\nTheater: " + theater +
                          "\nDate: " + date +
                          "\nTime: " + time);
            }

            cursor.close();
            db.close();

            return result;
        }

    //This method returns the theater name and ID when the user searches for the theater name
    //The method works similar to the getMovieSearchResult method defined above
        public String getTheaterSearchResult(String search){

            SQLiteDatabase db = this.getWritableDatabase();
            String result ="";

            //String array represents columns of the theater table
            String [] columns ={THEATER_NAME_COL,THEATER_ID_COL};

            //Search table for table name matching search parameter
            Cursor cursor = db.query(THEATER_TABLE_NAME,columns,
                    THEATER_NAME_COL+"= '"+search+"'" ,
                    null,null,null,null);


            while(cursor.moveToNext()){

                //indices is initialized using the cursor and the getColumnIndex function to return the
                //index of the corresponding columns passed to getColumnIndex
                int index0 = cursor.getColumnIndex(THEATER_NAME_COL);
                int index1 = cursor.getColumnIndex(THEATER_ID_COL);

                //Using index, retrieve information from the entry where the search matches through
                //the cursor object. Save the entry to a variable for output.
                String theaterName = cursor.getString(index0);
                int theaterID = cursor.getInt(index1);


                //Generate search result string, which contains the information retrieved from the table entry
                result = ("\nTheater: "+ theaterName +
                        "\nTheater ID: " + theaterID);
            }
            cursor.close();
            db.close();


            return result;
        }

    //This method will accept a time and date as arguments and us a query to search the showtime
    //table for movies playing on that date, at that time, and return the information to the user
    public String getShowtimeSearchResult(String date, String time){

        SQLiteDatabase db = this.getWritableDatabase();

        String result = "";

        //String array to represent the columns of the table in the query function
        String[] columns = {DATE_COL,TIME_COL, THEATER_NAME_COL, MOVIE_TITLE_COL};

        //This query will use two arguments for comparison, so it requires them to be passed as a string array
        String[] selectionArgs ={date,time};

        //cursor object will use a query to search the showtime table, in the movie title column,
        //for a string that matches the user
        Cursor cursor = db.query(SHOWTIME_TABLE_NAME,columns,
                DATE_COL+"=? AND " + TIME_COL + "=?",
                selectionArgs,null,null,null);


        while(cursor.moveToNext()){

            //indices is initialized using the cursor and the getColumnIndex function to return the
            //index of the corresponding columns passed to getColumnIndex
            int index0 = cursor.getColumnIndex(DATE_COL);
            int index1 = cursor.getColumnIndex(TIME_COL);
            int index2 = cursor.getColumnIndex(THEATER_NAME_COL);
            int index3 = cursor.getColumnIndex(MOVIE_TITLE_COL);

            //Using index, retrieve information from the entry where the search matches through
            //the cursor object. Save the entry to a string variable for output.
            String dateResult = cursor.getString(index0);
            String timeResult = cursor.getString(index1);
            String theater = cursor.getString(index2);
            String title = cursor.getString(index3);


            //Generate search result string, which contains the information retrieved from the table entry
            result = ("\nMovie: "+ title +
                    "\nTheater: " + theater +
                    "\nDate: " + dateResult +
                    "\nTime: " + timeResult);
        }

        cursor.close();
        db.close();


        return result;
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Do nothing here, will not be rebuilding the database
    }

}

