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
import com.lunareclipse.bargy.data.GlossaryAdapter;
import com.lunareclipse.bargy.data.LanguagesAdapter;
import com.lunareclipse.bargy.model.Language;
import com.lunareclipse.bargy.model.Phrase;
import com.lunareclipse.bargy.service.LanguageClient;
import com.lunareclipse.bargy.service.PhraseClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlossaryFragment extends Fragment {

    // Context
    private Context mContext;

    // RecyclerView
    @BindView(R.id.rv_glossary) RecyclerView mRecyclerView;

    // GlossaryAdapter
    private GlossaryAdapter mAdapter;

    // TextView for Error Messaging
    @BindView(R.id.tv_error_message_display_glossary) TextView mErrorTextView;

    // ProgressBar for Loading
    @BindView(R.id.pb_loading_indicator_glossary) ProgressBar mLoadingIndicator;

    // Key for Recycler Layout
    private static final String BUNDLE_RECYCLER_LAYOUT = "GlossaryFragment.recycler.layout";

    public GlossaryFragment() {
        // Required empty public constructor
    }

    // Inflates the RecyclerView
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        // Context
        mContext = getActivity();

        final View rootView = inflater.inflate(R.layout.fragment_glossary, container, false);

        // Bind Butterknife variables
        ButterKnife.bind(this, rootView);

        // Set to true as languages are fixed
        mRecyclerView.setHasFixedSize(true);

        // Display the loading icon
        mLoadingIndicator.setVisibility(View.VISIBLE);

        // Create a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialize the RecyclerView adapter, GlossaryAdapter
        mAdapter = new GlossaryAdapter(mContext);

        // Set the adapter
        mRecyclerView.setAdapter(mAdapter);

        // Determine what language we are working with for our glossary
        Intent intent = getActivity().getIntent();
        Language language = (Language) intent.getSerializableExtra("language");
        String languageName = language.getName().toLowerCase();

        // Construct the Retrofit builder
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://bargy-ed9f0.firebaseapp.com/" + languageName + "/")
                .addConverterFactory(GsonConverterFactory.create());

        // Create the retrofit objects
        Retrofit retrofit = builder.build();

        // Make the request and return the call object
        PhraseClient client = retrofit.create(PhraseClient.class);
        Call<ArrayList<Phrase>> call = client.getPhrases();

        // As we're in UI thread, we need to make the network call asynchronously, we do this using enqueue
        call.enqueue(new Callback<ArrayList<Phrase>>() {
            @Override
            public void onResponse(Call<ArrayList<Phrase>> call, Response<ArrayList<Phrase>> response) {
                ArrayList<Phrase> phrases = response.body();

                // Pass the phrases from the response into the adapter
                mAdapter.setPhrases(phrases);

                // Hide the loading icon
                mLoadingIndicator.setVisibility(View.INVISIBLE);

                // Reload the adapter
                onViewStateRestored(savedInstanceState);

            }

            @Override
            public void onFailure(Call<ArrayList<Phrase>> call, Throwable t) {
                // Hide the loading icon
                mLoadingIndicator.setVisibility(View.INVISIBLE);
                // Display the error message
                mErrorTextView.setVisibility(View.VISIBLE);
            }
        });

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, mRecyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null)
        {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }
}
