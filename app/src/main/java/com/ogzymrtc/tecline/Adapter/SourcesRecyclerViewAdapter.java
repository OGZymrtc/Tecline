package com.ogzymrtc.tecline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ogzymrtc.tecline.Helper.SourceRecyclerViewHelper;
import com.ogzymrtc.tecline.Interface.I;
import com.ogzymrtc.tecline.Pages.NewsFeed;
import com.ogzymrtc.tecline.R;

import java.util.ArrayList;

public class SourcesRecyclerViewAdapter extends RecyclerView.Adapter<SourcesRecyclerViewAdapter.MyViewHolder>{
    ArrayList<SourceRecyclerViewHelper> featured;
    private Context mContext;
    public SourcesRecyclerViewAdapter(ArrayList featured, Context context) {
        this.featured = featured;
        mContext= context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SourceRecyclerViewHelper sourceRecyclerViewHelper = featured.get(position);
        holder.imageView.setImageResource(sourceRecyclerViewHelper.getImage());
        holder.sourceName.setText(sourceRecyclerViewHelper.getName());
        holder.setItemClickListener(new I() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(mContext, NewsFeed.class);
                switch (position){
                    case 0:
                        intent.putExtra("source", "cw");
                        mContext.startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("source", "techr");
                        mContext.startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("source", "cnet");
                        mContext.startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra("source", "at");
                        mContext.startActivity(intent);
                        break;
                    case 4:
                        intent.putExtra("source", "vox");
                        mContext.startActivity(intent);
                        break;

                    case 5:
                        intent.putExtra("source", "tet");
                        mContext.startActivity(intent);
                        break;

                    case 6:
                        intent.putExtra("source", "twp");
                        mContext.startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return featured.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView sourceName;
        private I itemClickListener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sourcesImage);
            sourceName = itemView.findViewById(R.id.sourceName);
            itemView.setOnClickListener(this);
        }
        public void setItemClickListener(I itemClickListener){
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }
}

