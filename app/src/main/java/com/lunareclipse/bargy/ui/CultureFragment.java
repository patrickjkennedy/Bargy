package com.lunareclipse.bargy.ui;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lunareclipse.bargy.R;
import com.lunareclipse.bargy.data.CultureAdapter;
import com.lunareclipse.bargy.model.Culture;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CultureFragment extends Fragment {

    // Context
    private Context mContext;

    // RecyclerView
    @BindView(R.id.rv_culture) RecyclerView mRecyclerView;

    // CultureAdapter
    private CultureAdapter mAdapter;

    // TextView for Error Messaging
    @BindView(R.id.tv_error_message_display_culture) TextView mErrorTextView;

    // ProgressBar for Loading
    @BindView(R.id.pb_loading_indicator_culture) ProgressBar mLoadingIndicator;

    // Key for Recycler Layout
    private static final String BUNDLE_RECYCLER_LAYOUT = "CultureFragment.recycler.layout";


    public CultureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        // Set context
        mContext = getActivity();

        final View rootView = inflater.inflate(R.layout.fragment_culture, container, false);

        // Bind Butterknife variables
        ButterKnife.bind(this, rootView);

        // Display the loading icon
        mLoadingIndicator.setVisibility(View.VISIBLE);

        // Create a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Setup click listener
        CultureAdapter.CultureAdapterClickListener listener = new CultureAdapter.CultureAdapterClickListener() {
            @Override
            public void onClick(View view, Culture culture) {
                // Start implicit intent
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + culture.getKey()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + culture.getKey()));
                try {
                    mContext.startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    mContext.startActivity(webIntent);
                }
            }
        };

        // Initialize the RecyclerView adapter, CultureAdapter
        mAdapter = new CultureAdapter(listener, mContext);

        // Set the adapter
        mRecyclerView.setAdapter(mAdapter);

        // Load data
        loadCulturalData(savedInstanceState);

        return rootView;

    }

    private void loadCulturalData(final Bundle bundle){

        // Initialize Firebase components
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

        // Get a reference to the child node of the language
        DatabaseReference mCultureDatabaseReference = mFirebaseDatabase.getReference("yola/culture");

        // Setup the Firebase database event listener
        ChildEventListener mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Culture culture = dataSnapshot.getValue(Culture.class);
                mAdapter.add(culture);
                mLoadingIndicator.setVisibility(View.INVISIBLE);
                onViewStateRestored(bundle);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        // Add the event listener
        mCultureDatabaseReference.addChildEventListener(mChildEventListener);
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
