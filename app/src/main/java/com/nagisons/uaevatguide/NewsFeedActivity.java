package com.nagisons.uaevatguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.nagisons.uaevatguide.models.NewsFeedItem;

import java.util.ArrayList;

public class NewsFeedActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Latest UAE VAT News");
    }

    private void populateUsersList() {
        // Construct the data source
        ArrayList<NewsFeedItem> arrayOfUsers = new ArrayList<NewsFeedItem>();

        NewsFeedItem nf1 = new NewsFeedItem();
        nf1.setNewsTitle("News Title 1");

        NewsFeedItem nf2 = new NewsFeedItem();
        nf2.setNewsTitle("News Title 2");

        arrayOfUsers.add(nf1);
        arrayOfUsers.add(nf2);

        // Create the adapter to convert the array to views
        CustomNewsFeedItemAdapter adapter = new CustomNewsFeedItemAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.newsListView);
        listView.setAdapter(adapter);
    }
}
