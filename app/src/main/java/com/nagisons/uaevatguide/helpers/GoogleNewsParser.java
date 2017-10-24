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

//    private String BASE_URL = "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fnews.google.com%2Fnews%2Frss%2Fsearch%2Fsection%2Fq%2Fuae%2520vat%2Fuae%2520vat%3Fhl%3Den%26ned%3Dus";
    private String BASE_URL = "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.bing.com%2Fnews%2Fsearch%3Fq%3Duae%2Bvat%26qft%3Dsortbydate%253d%25221%2522%26format%3Drss";
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
                news.setNewsTitle(oneObject.getString("title").replace("&amp;","&"));
                String purelink = oneObject.getString("link");
                System.out.println("Old Link -> " + purelink);
                Pattern pattern = Pattern.compile("(?:url=)(.*?)(?:&amp;)");
                Matcher matcher = pattern.matcher(purelink);
                while (matcher.find()) {
                    purelink = matcher.group(1);
                }
                System.out.println("New Link -> " + purelink);
                String link = java.net.URLDecoder.decode(purelink, "UTF-8");
                news.setNewsUrl(link);
//                news.setNewsTime(oneObject.getString("pubDate"));
                Date date = inputFormat.parse(oneObject.getString("pubDate"));
                news.setNewsDate(date);
                String niceDateStr = (String) DateUtils.getRelativeTimeSpanString(date.getTime() , Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS);
                news.setNewsTime(niceDateStr);
                pattern = Pattern.compile("^(?:http:\\/\\/|www\\.|https:\\/\\/)([^\\/]+)");
                matcher = pattern.matcher(link);
                while (matcher.find()) {
                    news.setNewsSource(matcher.group(1).replace("www.",""));
                }
                news.setNewsSummary(oneObject.getString("description").replace("&amp;","&"));
                feeds.add(news);
            } catch (JSONException e) {
                // Oops
            }
        }
    }


}
