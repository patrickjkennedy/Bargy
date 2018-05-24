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
import com.lunareclipse.bargy.model.Language;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.ViewHolder> {

    private Context mContext;

    private HomeAdapterClickListener mListener;

    // ArrayList of Languages
    private ArrayList<Language> mLanguages = new ArrayList<>();

    /**
     * The interface for custom RecyclerViewClickListener
     */
    public interface HomeAdapterClickListener {
        void onClick(View view, Language language);
    }

    public LanguagesAdapter(HomeAdapterClickListener listener, Context context) {
        this.mListener = listener;
        this.mContext = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_home_fragment_item) ImageView itemImage;
        @BindView(R.id.tv_home_fragment_language_name) TextView itemName;
        @BindView(R.id.tv_home_fragment_language_origin) TextView itemOrigin;
        @BindView(R.id.tv_home_fragment_language_type) TextView itemType;
        @BindView(R.id.tv_home_fragment_language_status) TextView itemStatus;

        public ViewHolder(View itemView, HomeAdapterClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, mLanguages.get(getAdapterPosition()));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_fragment_card_item, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(mContext)
                .load(mLanguages.get(position).getImage())
                .into(holder.itemImage);

        holder.itemName.setText(mLanguages.get(position).getName());
        holder.itemOrigin.setText(mLanguages.get(position).getFullOrigin());
        holder.itemType.setText(mLanguages.get(position).getFullType());
        holder.itemStatus.setText(mLanguages.get(position).getFullStatus());
    }

    @Override
    public int getItemCount() {
        if (null == mLanguages) return 0;
        return mLanguages.size();
    }

    public void setLanguages(ArrayList<Language> languages){
        mLanguages = languages;
        notifyDataSetChanged();
    }
}
