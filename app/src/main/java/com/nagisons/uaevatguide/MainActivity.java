package com.nagisons.uaevatguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;

    private CardView latestNews;
    private CardView vatlaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("UAE VAT Guide");

        this.latestNews = (CardView) findViewById(R.id.latestNewsCard);
        this.vatlaw = (CardView) findViewById(R.id.vatLawCard);

        this.latestNews.setOnClickListener(this);
        this.vatlaw.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.latestNewsCard:
                Intent myIntent = new Intent(MainActivity.this, NewsFeedActivity.class);
                MainActivity.this.startActivity(myIntent);
        }

    }

}
