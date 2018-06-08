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
import com.lunareclipse.bargy.model.Phrase;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GlossaryAdapter extends RecyclerView.Adapter<GlossaryAdapter.ViewHolder> {

    private Context mContext;

    // ArrayList of Phrases
    private ArrayList<Phrase> mGlossary = new ArrayList<>();

    public GlossaryAdapter(Context context) {
        this.mContext = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.language_phrase) TextView languagePhrase;
        @BindView(R.id.translated_phrase) TextView translatedPhrase;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_glossary_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Bind the data from the Phrase object to the view
        holder.languagePhrase.setText(mGlossary.get(position).getPhrase());
        holder.translatedPhrase.setText(mGlossary.get(position).getTranslated_Phrase());
    }

    @Override
    public int getItemCount() {
        if (null == mGlossary) return 0;
        return mGlossary.size();
    }

    public void setPhrases(ArrayList<Phrase> glossary){
        mGlossary = glossary;
        notifyDataSetChanged();
    }

    public void add(Phrase phrase){
        mGlossary.add(phrase);
        notifyDataSetChanged();
    }
}
