package com.ogzymrtc.tecline.Fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ogzymrtc.tecline.Adapter.FeedAdapter;
import com.ogzymrtc.tecline.Common.HTTPDataHandler;
import com.ogzymrtc.tecline.Model.RssObject;
import com.ogzymrtc.tecline.R;


public class TopStoriesFragment extends Fragment {
    RecyclerView recyclerView;
    RssObject rssObject;

    private final String RSS_link = "https://www.wired.com/feed/rss";
    private final String RSS_to_JSON_API = " https://api.rss2json.com/v1/api.json?rss_url=";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_stories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.topStoriesRecycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        loadRSS();
        super.onViewCreated(view, savedInstanceState);
    }
    private void loadRSS(){
        AsyncTask<String, String, String> loadRssAsync = new AsyncTask<String, String, String>() {
            ProgressDialog progressDialog = new ProgressDialog(getContext());

            @Override
            protected void onPreExecute() {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }
            @Override
            protected String doInBackground(String... params) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(params[0]);
                return result;

            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //gson java2json
                rssObject = new Gson().fromJson(s, RssObject.class);
                FeedAdapter adapter = new FeedAdapter(rssObject, getContext());
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }
        };
        StringBuilder url_get_data = new StringBuilder(RSS_to_JSON_API);
        url_get_data.append(RSS_link);
        loadRssAsync.execute(url_get_data.toString());

    }

}
