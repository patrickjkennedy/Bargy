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
import com.lunareclipse.bargy.model.Culture;
import com.lunareclipse.bargy.model.Language;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CultureAdapter extends RecyclerView.Adapter<CultureAdapter.ViewHolder> {

    private Context mContext;

    private CultureAdapterClickListener mListener;

    // ArrayList of Cultural Objects
    private ArrayList<Culture> mCulturalObjects = new ArrayList<>();

    /**
     * The interface for custom RecyclerViewClickListener
     */
    public interface CultureAdapterClickListener {
        void onClick(View view, Culture culture);
    }

    public CultureAdapter(CultureAdapterClickListener listener, Context context) {
        this.mListener = listener;
        this.mContext = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_type_culture) TextView itemType;
        @BindView(R.id.tv_description_culture) TextView itemDescription;

        public ViewHolder(View itemView, CultureAdapterClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, mCulturalObjects.get(getAdapterPosition()));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cultural_item, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemType.setText(mCulturalObjects.get(position).getType());
        holder.itemDescription.setText(mCulturalObjects.get(position).getDescription());
        holder.itemDescription.setSelected(true);
    }

    @Override
    public int getItemCount() {
        if (null == mCulturalObjects) return 0;
        return mCulturalObjects.size();
    }

    public void add(Culture object){
        mCulturalObjects.add(object);
        notifyDataSetChanged();
    }
}
