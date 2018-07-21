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
import com.lunareclipse.bargy.model.History;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context mContext;

    // ArrayList of History Objects
    private ArrayList<History> mHistoricalObjects = new ArrayList<>();

    public HistoryAdapter(Context context){
        this.mContext = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_summary) TextView itemSummary;
        @BindView(R.id.tv_detail) TextView itemDetail;
        @BindView(R.id.iv_image_one) ImageView itemImageViewOne;
        @BindView(R.id.tv_image_one_description) TextView itemImageOneDescription;
        @BindView(R.id.tv_image_one_attribution) TextView itemImageOneAttribution;
        @BindView(R.id.iv_image_two) ImageView itemImageViewTwo;
        @BindView(R.id.tv_image_two_description) TextView itemImageTwoDescription;
        @BindView(R.id.tv_image_two_attribution) TextView itemImageTwoAttribution;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_historical_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemSummary.setText(mHistoricalObjects.get(position).getSummary());
        holder.itemDetail.setText(mHistoricalObjects.get(position).getDetail());

        // Use Glide to download and set the first image.
        Glide.with(mContext)
                .load(mHistoricalObjects.get(position).getPicture_One_Link())
                .into(holder.itemImageViewOne);

        holder.itemImageOneDescription.setText(mHistoricalObjects.get(position).getPicture_One_Description());
        holder.itemImageOneAttribution.setText(mHistoricalObjects.get(position).getPicture_One_Attribution());

        // Use Glide to download and set the second image.
        Glide.with(mContext)
                .load(mHistoricalObjects.get(position).getPicture_Two_Link())
                .into(holder.itemImageViewTwo);

        holder.itemImageTwoDescription.setText(mHistoricalObjects.get(position).getPicture_Two_Description());
        holder.itemImageTwoAttribution.setText(mHistoricalObjects.get(position).getPicture_Two_Attribution());
    }

    @Override
    public int getItemCount() {
        if (null == mHistoricalObjects) return 0;
        return mHistoricalObjects.size();
    }

    public void add(History object){
        mHistoricalObjects.add(object);
        notifyDataSetChanged();
    }
}
