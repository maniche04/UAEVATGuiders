package com.nagisons.uaevatguide;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.nagisons.uaevatguide.helpers.GoogleNewsParser;
import com.nagisons.uaevatguide.models.NewsFeedItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NewsFeedActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView newsList;
    private GoogleNewsParser googleNews;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Latest UAE VAT News");

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        this.populateUsersList();
        /*
         * Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
         * performs a swipe-to-refresh gesture.
         */
        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        populateUsersList();

                    }
                }
        );

    }

    private void populateUsersList() {
        // Construct the data source
        mSwipeRefreshLayout.setRefreshing(true);
        googleNews = new GoogleNewsParser();
        new RetrieveFeedTask().execute("none");
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, ArrayList<NewsFeedItem>> {

        private Exception exception;

        protected ArrayList<NewsFeedItem> doInBackground(String... urls) {
            try {
                try {
                    googleNews.fetchNews();
                    Collections.sort(googleNews.feeds, new Comparator<NewsFeedItem>() {
                        @Override
                        public int compare(NewsFeedItem o1, NewsFeedItem o2) {
                            return o2.getNewsDate().compareTo(o1.getNewsDate());
                        }
                    });
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
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);
            mSwipeRefreshLayout.setRefreshing(false);
        }

    }
}
