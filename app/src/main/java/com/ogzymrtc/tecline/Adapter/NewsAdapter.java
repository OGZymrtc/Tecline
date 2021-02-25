package com.ogzymrtc.tecline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ogzymrtc.tecline.Interface.I;
import com.ogzymrtc.tecline.Model.RssObject;
import com.ogzymrtc.tecline.Pages.DetailsActivity;
import com.ogzymrtc.tecline.R;

class NewsViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txtTitle, txtDescription;
    public ImageView imageView;
    private I itemClickListener;
    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTitle = itemView.findViewById(R.id.title);
        txtDescription = itemView.findViewById(R.id.description);
        imageView = itemView.findViewById(R.id.imageView);
        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(I itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }
}


public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    private RssObject rssObject;
    private Context mContext;
    private LayoutInflater inflater;

    public NewsAdapter(RssObject rssObject, Context mContext) {
        this.rssObject = rssObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        final String url,thumbnailUrl,imageUrl;
        holder.txtTitle.setText(rssObject.getItems().get(position).getTitle());
        holder.txtDescription.setText(rssObject.getItems().get(position).getPubDate());
        thumbnailUrl = rssObject.getItems().get(position).getThumbnail();
        if (thumbnailUrl !="" && thumbnailUrl != null){Glide.with(mContext).load(thumbnailUrl).into(holder.imageView); }
        imageUrl =rssObject.getItems().get(position).getEnclosure().getLink();
        if (imageUrl != "" && imageUrl!=null){Glide.with(mContext).load(imageUrl).into(holder.imageView);}
        url = rssObject.getItems().get(position).getLink();
        holder.setItemClickListener(new I() {
            @Override
            public void onClick(View view, int position) {
                Intent intent= new Intent(mContext, DetailsActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title",rssObject.getItems().get(position).getTitle());
                if (thumbnailUrl !="" && thumbnailUrl != null){
                    intent.putExtra("image",thumbnailUrl);
                }
                if (imageUrl != "" && imageUrl !=null){
                    intent.putExtra("image",imageUrl);
                }
                intent.putExtra("fromAdapter","yes");
                mContext.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return rssObject.items.size();
    }
}
