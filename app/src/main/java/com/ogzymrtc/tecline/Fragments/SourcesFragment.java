package com.ogzymrtc.tecline.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ogzymrtc.tecline.Helper.SourceRecyclerViewHelper;
import com.ogzymrtc.tecline.Adapter.SourcesRecyclerViewAdapter;
import com.ogzymrtc.tecline.R;

import java.util.ArrayList;

public class SourcesFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    AppCompatImageView imageview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sources, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.sourcesRecycler);
        imageview = view.findViewById(R.id.sourcesImage);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<SourceRecyclerViewHelper> featured = new ArrayList<>();
        featured.add(new SourceRecyclerViewHelper(R.drawable.computerworld, "Computerworld"));
        featured.add(new SourceRecyclerViewHelper(R.drawable.tec, "Techradar"));
        featured.add(new SourceRecyclerViewHelper(R.drawable.cnet, "Cnet"));
        featured.add(new SourceRecyclerViewHelper(R.drawable.ars, "Arstechnica"));
        featured.add(new SourceRecyclerViewHelper(R.drawable.vox, "Vox"));
        featured.add(new SourceRecyclerViewHelper(R.drawable.tet, "The Economic Times"));
        featured.add(new SourceRecyclerViewHelper(R.drawable.twp, "The Washington Post"));
        adapter = new SourcesRecyclerViewAdapter(featured, getContext());
        recyclerView.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);
    }
}
