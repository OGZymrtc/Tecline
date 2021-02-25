package com.ogzymrtc.tecline.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.ogzymrtc.tecline.Fragments.FavoritesFragment;
import com.ogzymrtc.tecline.R;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    WebView webView;
    String title, url, imageUrl,from;
    ImageView favButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        favButton = findViewById(R.id.favButton);
        webView = findViewById(R.id.webView);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        imageUrl =intent.getStringExtra("image");
        from = intent.getStringExtra("fromAdapter");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }
        });
        webView.loadUrl(url);
        if (from.matches("no"))
            favButton.setBackground(getDrawable(R.drawable.ic_baseline_red_favorite_24));
        else
        checkTitle(title);

    }
    public void favButtonClick(View v){
             favButton.setBackground(getDrawable(R.drawable.ic_baseline_red_favorite_24));
            if (url != null && title != null && imageUrl != null && from.matches("yes")){
                if (checkDb(title)){
                    Toast.makeText(this, "This news added to favorite list", Toast.LENGTH_SHORT).show();
                    try {
                        SQLiteDatabase database = this.openOrCreateDatabase("Favorites", MODE_PRIVATE, null);
                        database.execSQL("CREATE TABLE IF NOT EXISTS favorites (id INTEGER PRIMARY KEY, title String, url String, imageUrl String)");
                        String sqlString = "INSERT INTO favorites (title, url, imageUrl) VALUES (?,?,?)";
                        SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
                        sqLiteStatement.bindString(1,title);
                        sqLiteStatement.bindString(2,url);
                        sqLiteStatement.bindString(3,imageUrl);
                        sqLiteStatement.execute();

                    }catch (Exception e){
                        System.out.println("Exception: "+e);
                    }
                }
                else{
                    favButton.setBackground(getDrawable(R.drawable.ic_baseline_favorite_24));
                    try {
                        SQLiteDatabase database = this.openOrCreateDatabase("Favorites", MODE_PRIVATE, null);
                        String sqlString = "DELETE FROM favorites WHERE title = ?";
                        SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
                        sqLiteStatement.bindString(1,title);
                        sqLiteStatement.execute();
                        Toast.makeText(this, "This news is removed from your favorites list", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        System.out.println("Exception: "+e);
                    }
                }
            }
            else {
                    favButton.setBackground(getDrawable(R.drawable.ic_baseline_favorite_24));
                    try {
                        SQLiteDatabase database = this.openOrCreateDatabase("Favorites", MODE_PRIVATE, null);
                        String sqlString = "DELETE FROM favorites WHERE title = ?";
                        SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
                        sqLiteStatement.bindString(1,title);
                        sqLiteStatement.execute();
                        Toast.makeText(this, "This news is removed from your favorites list", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        System.out.println("Exception: "+e);
                    }

            }
    }
    public void checkTitle(String title){
        ArrayList<String> titleArray = new ArrayList<>();
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Favorites", Context.MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM favorites", null);
            int titleIx = cursor.getColumnIndex("title");
            if (titleIx != 0 ){
                while (cursor.moveToNext()){
                    titleArray.add(cursor.getString(titleIx));
                }
                cursor.close();
            }
        }catch (Exception e){
            System.out.println("Exception: "+e);
        }
        if (title != null && titleArray != null){
            for (int i=0 ; i < titleArray.size() ; i++){
                if (title.matches(titleArray.get(i))){
                    favButton.setBackground(getDrawable(R.drawable.ic_baseline_red_favorite_24));
                }
            }
        }
    }
    public boolean checkDb(String title){
        ArrayList<String> titleArray = new ArrayList<>();
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Favorites", Context.MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM favorites", null);
            int titleIx = cursor.getColumnIndex("title");
            if (titleIx != 0 ){
                while (cursor.moveToNext()){

                    titleArray.add(cursor.getString(titleIx));
                }
                cursor.close();
            }
        }
       catch (Exception e){
           System.out.println("Exception: "+e);
       }
        if (title != null && titleArray != null){
            for (int i=0 ; i < titleArray.size() ; i++){
                if (title.matches(titleArray.get(i))){
                    return false;
                }
            }
        }
        return true;
    }

}