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

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txtTitle, txtDescription;
    public ImageView imageView;
    private I itemClickListener;

    public FeedViewHolder(View view){
        super(view);
        txtTitle = view.findViewById(R.id.title);
        txtDescription = view.findViewById(R.id.description);
        imageView = view.findViewById(R.id.imageView);

        view.setOnClickListener(this);

    }

    public void setItemClickListener(I itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }
}

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder>{
    private RssObject rssObject;
    private Context mContext;
    private LayoutInflater inflater;

    public FeedAdapter(RssObject rssObject, Context mContext) {
        this.rssObject = rssObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        final String url,imgUrl;
        holder.txtTitle.setText(rssObject.getItems().get(position).getTitle());
        holder.txtDescription.setText(rssObject.getItems().get(position).getPubDate());
        imgUrl = rssObject.getItems().get(position).getThumbnail();
        if (imgUrl !="" && imgUrl != null){
            Glide.with(mContext).load(imgUrl).into(holder.imageView); }
        url = rssObject.getItems().get(position).getLink();
        holder.setItemClickListener(new I() {
            @Override
            public void onClick(View view, int position) {
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra("url",url);
                    intent.putExtra("title",rssObject.getItems().get(position).getTitle());
                    if (imgUrl !="" && imgUrl !=null ){
                        intent.putExtra("image", imgUrl);
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
