package com.ogzymrtc.tecline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ogzymrtc.tecline.Interface.I;
import com.ogzymrtc.tecline.Pages.DetailsActivity;
import com.ogzymrtc.tecline.R;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteViewholder>{
    ArrayList<String> titleArray, urlArray, imageArray;
    Context mContext;
    LayoutInflater inflater;
    public FavoriteAdapter(ArrayList title, ArrayList url, ArrayList image, Context mContext) {
        this.titleArray = title;
        this.urlArray = url;
        this.imageArray = image;
        this.mContext =mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public FavoriteViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.favorite_card_design, parent, false);
        return new FavoriteViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewholder holder, int position) {
        final String url, image;
        holder.title.setText(titleArray.get(position));
        image = imageArray.get(position);
        if (image != null && image != ""){ Glide.with(mContext).load(image).into(holder.imageView); }
        url = urlArray.get(position);
        holder.setItemClickListener(new I() {
            @Override
            public void onClick(View view, int position) {
                Intent intent= new Intent(mContext, DetailsActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title", titleArray.get(position));
                intent.putExtra("fromAdapter", "no");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleArray.size();
    }
}
class FavoriteViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView title;
    ImageView imageView;
    private I itemClickListener;
    public FavoriteViewholder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.favTitle);
        imageView = itemView.findViewById(R.id.favImage);
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
