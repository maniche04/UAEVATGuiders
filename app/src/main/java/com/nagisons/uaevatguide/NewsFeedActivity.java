package com.nagisons.uaevatguide;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.nagisons.uaevatguide.helpers.GoogleNewsParser;
import com.nagisons.uaevatguide.models.NewsFeedItem;

import java.util.ArrayList;

public class NewsFeedActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView newsList;
    private GoogleNewsParser googleNews = new GoogleNewsParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Latest UAE VAT News");
        this.populateUsersList();
    }

    private void populateUsersList() {
        // Construct the data source
        new RetrieveFeedTask().execute("none");
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, ArrayList<NewsFeedItem>> {

        private Exception exception;

        protected ArrayList<NewsFeedItem> doInBackground(String... urls) {
            try {
                try {
                    googleNews.fetchNews();
                    return googleNews.feeds;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                this.exception = e;
            }
            return null;
        }

        protected void onPostExecute(ArrayList<NewsFeedItem> feeds) {
            CustomNewsFeedItemAdapter adapter = new CustomNewsFeedItemAdapter(NewsFeedActivity.this, feeds);
            // Attach the adapter to a ListView
            ListView listView = (ListView) findViewById(R.id.newsListView);
            listView.setAdapter(adapter);
        }

    }
}
