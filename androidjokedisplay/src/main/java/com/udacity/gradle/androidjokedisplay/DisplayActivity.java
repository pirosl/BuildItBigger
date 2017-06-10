package com.udacity.gradle.androidjokedisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    public static final String JOKE_TEXT = "com.udacity.gradle.androidjokedisplay.JOKE_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        TextView textJoke = (TextView)findViewById(R.id.textJoke);

        String joke = getIntent().getStringExtra(JOKE_TEXT);
        textJoke.setText(joke);
    }
}
