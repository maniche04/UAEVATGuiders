package com.nagisons.uaevatguide;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nagisons.uaevatguide.R;
import com.nagisons.uaevatguide.models.NewsFeedItem;

public class CustomNewsFeedItemAdapter extends ArrayAdapter<NewsFeedItem> {
    public CustomNewsFeedItemAdapter(Context context, ArrayList<NewsFeedItem> newsfeeds) {
        super(context, 0, newsfeeds);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.component_news_single, parent, false);
        }

        // Get the data item for this position
        final NewsFeedItem newsItem = getItem(position);

        // Lookup view for data population
        TextView newsTitle = (TextView) convertView.findViewById(R.id.newsTitle);
        // Populate the data into the template view using the data object
        newsTitle.setText(newsItem.getNewsTitle());
        // Return the completed view to render on screen
        TextView newsTime = (TextView) convertView.findViewById(R.id.newsTime);
        // Populate the data into the template view using the data object
        newsTime.setText(newsItem.getNewsTime());
        TextView newsSource = (TextView) convertView.findViewById(R.id.newsSource);
        // Populate the data into the template view using the data object
        newsSource.setText(newsItem.getNewsSource());
        TextView newsSummary = (TextView) convertView.findViewById(R.id.newsSummary);
        // Populate the data into the template view using the data object
        newsSummary.setText(newsItem.getNewsSummary());


        convertView.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Opening: " + newsItem.getNewsUrl());
                Uri uri = Uri.parse(newsItem.getNewsUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}