package com.lunareclipse.bargy.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.lunareclipse.bargy.R;
import com.lunareclipse.bargy.data.LanguagesAdapter;
import com.lunareclipse.bargy.model.Language;
import com.lunareclipse.bargy.service.LanguageClient;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryFragment extends Fragment {

    // Context
    private Context mContext;

    // TextView for Error Messaging
    @BindView(R.id.tv_history_error_message_display) TextView mErrorTextView;

    // ProgressBar for Loading
    @BindView(R.id.pb_history_loading_indicator) ProgressBar mLoadingIndicator;

    // Key for Recycler Layout
    private static final String BUNDLE_RECYCLER_LAYOUT = "HistoryFragment.recycler.layout";

    public HistoryFragment() {
        // Required empty public constructor
    }

    // Inflates the RecyclerView containing the layouts for each history item
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        // Context
        mContext = getActivity();

        final View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        // Bind Butterknife variables
        ButterKnife.bind(this, rootView);

        // Display the loading icon
        mLoadingIndicator.setVisibility(View.VISIBLE);



        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, mRecyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null)
        {
            //Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            //mRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }
}
