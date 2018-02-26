package com.example.imtiazaminsajid.myfavouritethings;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button movie, book, food, place;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movie = findViewById(R.id.movie);
        book = findViewById(R.id.book);
        food = findViewById(R.id.food);
        place = findViewById(R.id.place);

    }



    public void movie(View view) {
        startActivity(new Intent(MainActivity.this, AddingMovieActivity.class));
    }

    public void food(View view) {
    }

    public void place(View view) {
    }

    public void book(View view) {
    }
}
