package com.nagisons.uaevatguide.helpers;

import android.text.format.DateUtils;

import com.nagisons.uaevatguide.models.NewsFeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by manish on 10/22/17.
 */

public class GoogleNewsParser {

    private String BASE_URL = "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fnews.google.com%2Fnews%2Frss%2Fsearch%2Fsection%2Fq%2Fuae%2520vat%2Fuae%2520vat%3Fhl%3Den%26ned%3Dus";
    private HttpClient http;

    public ArrayList<NewsFeedItem> feeds = new ArrayList<NewsFeedItem>();
    public static final SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public GoogleNewsParser() {
        this.http = new HttpClient();
    }

    public void fetchNews() throws Exception {


        String result = this.http.getRequest(BASE_URL);
        JSONObject jObject = new JSONObject(result);
        JSONArray jArray = jObject.getJSONArray("items");
        for (int i=0; i < jArray.length(); i++)
        {
            try {
                JSONObject oneObject = jArray.getJSONObject(i);
                // Pulling items from the array
                NewsFeedItem news = new NewsFeedItem();
                news.setNewsTitle(oneObject.getString("title"));
                news.setNewsUrl(oneObject.getString("link"));
//                news.setNewsTime(oneObject.getString("pubDate"));
                Date date = inputFormat.parse(oneObject.getString("pubDate"));
                String niceDateStr = (String) DateUtils.getRelativeTimeSpanString(date.getTime() , Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS);
                news.setNewsTime(niceDateStr);

                Pattern pattern = Pattern.compile("^(?:http:\\/\\/|www\\.|https:\\/\\/)([^\\/]+)");
                Matcher matcher = pattern.matcher(oneObject.getString("link"));
                while (matcher.find()) {
                    news.setNewsSource(matcher.group(1));
                }

                feeds.add(news);
            } catch (JSONException e) {
                // Oops
            }
        }
    }


}
