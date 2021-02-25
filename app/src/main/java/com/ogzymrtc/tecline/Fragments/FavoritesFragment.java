package com.ogzymrtc.tecline.Fragments;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ogzymrtc.tecline.Adapter.FavoriteAdapter;

import com.ogzymrtc.tecline.R;

import java.util.ArrayList;


public class FavoritesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    ArrayList<String> titleArray, urlArray, imageUrlArray;
    SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        swipeRefresh(view);

        recyclerView = view.findViewById(R.id.favoriteRecycler);

        titleArray = new ArrayList<>();
        urlArray = new ArrayList<>();
        imageUrlArray = new ArrayList<>();

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        getData();

        return view;
    }

    private void swipeRefresh(View view) {
        swipeRefreshLayout = view.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(1, 2, 3, 4);
    }

    public void getData(){
       try {
           SQLiteDatabase database = getContext().openOrCreateDatabase("Favorites", Context.MODE_PRIVATE, null);
           Cursor cursor = database.rawQuery("SELECT * FROM favorites", null);
           int titleIx = cursor.getColumnIndex("title");
           int urlIx = cursor.getColumnIndex("url");
           int imageIx= cursor.getColumnIndex("imageUrl");

           while (cursor.moveToNext()){
               titleArray.add(cursor.getString(titleIx));
               urlArray.add(cursor.getString(urlIx));
               imageUrlArray.add(cursor.getString(imageIx));
           }
           cursor.close();
       }catch (Exception e){
           System.out.println("Exception: "+e);
       }
        FavoriteAdapter adapter = new FavoriteAdapter(titleArray, urlArray, imageUrlArray, getContext());
       recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.detach(currentFragment);
        fragmentTransaction.attach(currentFragment);
        fragmentTransaction.commit();
    }
}
