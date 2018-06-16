package com.lunareclipse.bargy.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class HomeFragment extends Fragment {

    // Context
    private Context mContext;

    // RecyclerView
    @BindView(R.id.rv_master_list) RecyclerView mRecyclerView;

    // LanguagesAdapter
    private LanguagesAdapter mAdapter;

    // TextView for Error Messaging
    @BindView(R.id.tv_error_message_display) TextView mErrorTextView;

    // ProgressBar for Loading
    @BindView(R.id.pb_loading_indicator) ProgressBar mLoadingIndicator;

    // Key for Recycler Layout
    private static final String BUNDLE_RECYCLER_LAYOUT = "LanguagesFragment.recycler.layout";

    public HomeFragment() {
        // Required empty public constructor
    }

    // Inflates the RecyclerView containing the CardView for each Language
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        // Context
        mContext = getActivity();

        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Bind Butterknife variables
        ButterKnife.bind(this, rootView);

        // Set to true as languages are fixed
        mRecyclerView.setHasFixedSize(true);

        // Display the loading icon
        mLoadingIndicator.setVisibility(View.VISIBLE);

        // Create a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Setup click listener
        LanguagesAdapter.HomeAdapterClickListener listener = new LanguagesAdapter.HomeAdapterClickListener() {
            @Override
            public void onClick(View view, Language language) {
                Class destinationClass = MenuActivity.class;
                Intent intent = new Intent(mContext, destinationClass);

                Bundle bundle = new Bundle();
                bundle.putSerializable("language", language);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        };

        // Initialize the RecyclerView adapter, LanguagesAdapter
        mAdapter = new LanguagesAdapter(listener, mContext);

        // Set the adapter
        mRecyclerView.setAdapter(mAdapter);

        // Construct the Retrofit builder
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://bargy-ed9f0.firebaseapp.com/")
                .addConverterFactory(GsonConverterFactory.create());

        // Create the retrofit objects
        Retrofit retrofit = builder.build();

        // Make the request and return the call object
        LanguageClient client = retrofit.create(LanguageClient.class);
        Call<ArrayList<Language>> call = client.getLanguages();

        // As we're in UI thread, we need to make the network call asynchronously, we do this using enqueue
        call.enqueue(new Callback<ArrayList<Language>>() {
            @Override
            public void onResponse(Call<ArrayList<Language>> call, Response<ArrayList<Language>> response) {
                ArrayList<Language> languages = response.body();

                // Pass the recipes from the response into the adapter
                mAdapter.setLanguages(languages);

                // Hide the loading icon
                mLoadingIndicator.setVisibility(View.INVISIBLE);

                // Reload the adapter
                onViewStateRestored(savedInstanceState);

            }

            @Override
            public void onFailure(Call<ArrayList<Language>> call, Throwable t) {
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
