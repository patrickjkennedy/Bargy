package com.lunareclipse.bargy.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lunareclipse.bargy.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LanguageMenuFragment extends Fragment {

    // Context
    private Context mContext;

    // Cardviews for Language Menu
    @BindView(R.id.cv_glossary) CardView mGlossaryOption;
    @BindView(R.id.cv_history) CardView mHistoryOption;
    @BindView(R.id.cv_culture) CardView mCultureOption;

    public LanguageMenuFragment() {
        // Required empty public constructor
    }

    // Inflates the RecyclerView containing the CardView for each Language
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        // Context
        mContext = getActivity();

        final View rootView = inflater.inflate(R.layout.fragment_language_menu, container, false);

        // Bind Butterknife variables
        ButterKnife.bind(this, rootView);

        mGlossaryOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create the glossary fragment
                Log.d("LanguageMenuFragment", "Glossary - ta da!");
            }
        });

        mHistoryOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create the history fragment
                Log.d("LanguageMenuFragment", "History - ta da!");

            }
        });

        mCultureOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create the culture fragment
                Log.d("LanguageMenuFragment", "Culture - ta da!");
            }
        });

        return rootView;
    }

}
