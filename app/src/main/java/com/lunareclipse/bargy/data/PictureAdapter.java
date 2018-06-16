package com.lunareclipse.bargy.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.lunareclipse.bargy.R;
import com.lunareclipse.bargy.model.Picture;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private Context mContext;

    // ArrayList of pictures
    private ArrayList<Picture> mPictures = new ArrayList<>();

    public PictureAdapter(Context context){
        this.mContext = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_picture_item) ImageView itemImage;
        @BindView(R.id.tv_description) TextView itemDescription;
        @BindView(R.id.tv_credit) TextView itemCredit;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.picture_card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mPictures.get(position).getLink())
                .into(holder.itemImage);

        holder.itemDescription.setText(mPictures.get(position).getDescription());
        holder.itemCredit.setText(mPictures.get(position).getAttribution());
    }

    @Override
    public int getItemCount() {
        if (null == mPictures) return 0;
        return mPictures.size();
    }

    public void setPictures(ArrayList<Picture> pictures){
        mPictures = pictures;
        notifyDataSetChanged();
    }
}
