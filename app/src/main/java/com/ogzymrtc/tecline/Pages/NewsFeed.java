package com.ogzymrtc.tecline.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;


import com.google.gson.Gson;
import com.ogzymrtc.tecline.Adapter.NewsAdapter;
import com.ogzymrtc.tecline.Common.HTTPDataHandler;
import com.ogzymrtc.tecline.Model.RssObject;
import com.ogzymrtc.tecline.R;

public class NewsFeed extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView sourceNameText;
    String source;
    RssObject rssObject;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        recyclerView= findViewById(R.id.newsRecycler);
        sourceNameText = findViewById(R.id.sourceNameText);
        gridLayoutManager = new GridLayoutManager(getBaseContext(), 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        Intent intent = getIntent();
        source = intent.getStringExtra("source");
       switch (source){
            case "twp":
                sourceNameText.setText("The Washington Post");
                getTheWahsingtonPostData();
                break;
           case "at":
               sourceNameText.setText("Arstechnica");
               getArstechnicaData();
               break;
           case "cw":
               sourceNameText.setText("COMPUTERWORLD");
               getCwData();
               break;
           case "vox":
               sourceNameText.setText("VOX");
               getVoxData();
               break;
           case "techr":
               sourceNameText.setText("Techradar");
               getTechradarData();
               break;
           case "tet":
               sourceNameText.setText("The Economic Times");
               getTheEconomicTimesData();
               break;
           case "cnet":
               sourceNameText.setText("Cnet");
               getCnetData();
               break;
        }
    }
    private void getTheWahsingtonPostData() {
        final String RSS_link ="http://feeds.washingtonpost.com/rss/business/technology";
        final String RSS_to_JSON_API = " https://api.rss2json.com/v1/api.json?rss_url=";
        AsyncTask<String, String, String> loadRss = new AsyncTask<String, String, String>() {
            ProgressDialog progressDialog =new ProgressDialog(NewsFeed.this);
            @Override
            protected void onPreExecute() {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }
            @Override
            protected String doInBackground(String... strings) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(strings[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                rssObject = new Gson().fromJson(s, RssObject.class);
                NewsAdapter adapter = new NewsAdapter(rssObject, NewsFeed.this);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }
        };
        StringBuilder url_get_data = new StringBuilder(RSS_to_JSON_API);
        url_get_data.append(RSS_link);
        loadRss.execute(url_get_data.toString());
    }
    private void getArstechnicaData() {
        final String RSS_link ="http://feeds.arstechnica.com/arstechnica/technology-lab";
        final String RSS_to_JSON_API = " https://api.rss2json.com/v1/api.json?rss_url=";
        AsyncTask<String, String, String> loadRss = new AsyncTask<String, String, String>() {
            ProgressDialog progressDialog =new ProgressDialog(NewsFeed.this);
            @Override
            protected void onPreExecute() {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }
            @Override
            protected String doInBackground(String... strings) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(strings[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                rssObject = new Gson().fromJson(s, RssObject.class);
                NewsAdapter adapter = new NewsAdapter(rssObject, NewsFeed.this);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }
        };
        StringBuilder url_get_data = new StringBuilder(RSS_to_JSON_API);
        url_get_data.append(RSS_link);
        loadRss.execute(url_get_data.toString());
    }
    private void getCwData() {
        final String RSS_link ="https://www.computerworld.com/news/index.rss";
        final String RSS_to_JSON_API = " https://api.rss2json.com/v1/api.json?rss_url=";
        AsyncTask<String, String, String> loadRss = new AsyncTask<String, String, String>() {
            ProgressDialog progressDialog =new ProgressDialog(NewsFeed.this);
            @Override
            protected void onPreExecute() {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }
            @Override
            protected String doInBackground(String... strings) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(strings[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                rssObject = new Gson().fromJson(s, RssObject.class);
                NewsAdapter adapter = new NewsAdapter(rssObject, NewsFeed.this);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }
        };
        StringBuilder url_get_data = new StringBuilder(RSS_to_JSON_API);
        url_get_data.append(RSS_link);
        loadRss.execute(url_get_data.toString());
    }
    private void getVoxData() {
        final String RSS_link ="https://www.vox.com/rss/recode/index.xml";
        final String RSS_to_JSON_API = "https://api.rss2json.com/v1/api.json?rss_url=";
        AsyncTask<String, String, String> loadRss = new AsyncTask<String, String, String>() {
            ProgressDialog progressDialog =new ProgressDialog(NewsFeed.this);
            @Override
            protected void onPreExecute() {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }
            @Override
            protected String doInBackground(String... strings) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(strings[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                rssObject = new Gson().fromJson(s, RssObject.class);
                NewsAdapter adapter = new NewsAdapter(rssObject, NewsFeed.this);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }
        };
        StringBuilder url_get_data = new StringBuilder(RSS_to_JSON_API);
        url_get_data.append(RSS_link);
        loadRss.execute(url_get_data.toString());
    }
    private void getTechradarData() {
        final String RSS_link ="https://www.techradar.com/rss";
        final String RSS_to_JSON_API = " https://api.rss2json.com/v1/api.json?rss_url=";
        AsyncTask<String, String, String> loadRss = new AsyncTask<String, String, String>() {
            ProgressDialog progressDialog =new ProgressDialog(NewsFeed.this);
            @Override
            protected void onPreExecute() {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }
            @Override
            protected String doInBackground(String... strings) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(strings[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                rssObject = new Gson().fromJson(s, RssObject.class);
                NewsAdapter adapter = new NewsAdapter(rssObject, NewsFeed.this);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }
        };
        StringBuilder url_get_data = new StringBuilder(RSS_to_JSON_API);
        url_get_data.append(RSS_link);
        loadRss.execute(url_get_data.toString());
    }
    private void getTheEconomicTimesData() {
        final String RSS_link ="https://economictimes.indiatimes.com/tech/rssfeeds/13357270.cms";
        final String RSS_to_JSON_API = " https://api.rss2json.com/v1/api.json?rss_url=";
        AsyncTask<String, String, String> loadRss = new AsyncTask<String, String, String>() {
            ProgressDialog progressDialog =new ProgressDialog(NewsFeed.this);
            @Override
            protected void onPreExecute() {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }
            @Override
            protected String doInBackground(String... strings) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(strings[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                rssObject = new Gson().fromJson(s, RssObject.class);
                NewsAdapter adapter = new NewsAdapter(rssObject, NewsFeed.this);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }
        };
        StringBuilder url_get_data = new StringBuilder(RSS_to_JSON_API);
        url_get_data.append(RSS_link);
        loadRss.execute(url_get_data.toString());
    }
    private void getCnetData() {
        final String RSS_link ="https://www.cnet.com/rss/news/";
        final String RSS_to_JSON_API = " https://api.rss2json.com/v1/api.json?rss_url=";
        AsyncTask<String, String, String> loadRss = new AsyncTask<String, String, String>() {
            ProgressDialog progressDialog =new ProgressDialog(NewsFeed.this);
            @Override
            protected void onPreExecute() {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }
            @Override
            protected String doInBackground(String... strings) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(strings[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                rssObject = new Gson().fromJson(s, RssObject.class);
                NewsAdapter adapter = new NewsAdapter(rssObject, NewsFeed.this);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }
        };
        StringBuilder url_get_data = new StringBuilder(RSS_to_JSON_API);
        url_get_data.append(RSS_link);
        loadRss.execute(url_get_data.toString());
    }
}