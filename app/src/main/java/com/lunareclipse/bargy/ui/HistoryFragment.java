package com.lunareclipse.bargy.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lunareclipse.bargy.R;
import com.lunareclipse.bargy.data.PictureAdapter;
import com.lunareclipse.bargy.model.History;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryFragment extends Fragment {

    // TextView for Error Messaging
    @BindView(R.id.tv_history_error_message_display) TextView mErrorTextView;

    // ProgressBar for Loading
    @BindView(R.id.pb_history_loading_indicator) ProgressBar mLoadingIndicator;

    // Views for content from FRD
    @BindView(R.id.sv_history) ScrollView mScrollView;
    @BindView(R.id.tv_summary) TextView mSummary;
    @BindView(R.id.tv_detail) TextView mDetail;
    @BindView(R.id.tv_summary_heading) TextView mSummaryHeading;
    @BindView(R.id.tv_detail_heading) TextView mDetailHeading;
    @BindView(R.id.tv_attribution) TextView mAttribution;
    @BindView(R.id.v_hdivider1) View mDivider1;
    @BindView(R.id.v_hdivider2) View mDivider2;

    // Recyclerview
    @BindView(R.id.rv_pictures) RecyclerView mRecyclerView;

    // PictureAdapter
    private PictureAdapter mAdapter;

    // Key for Scrollview position
    private static final String ARTICLE_SCROLL_POSITION = "ARTICLE_SCROLL_POSITION";

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        // Context
        Context mContext = getActivity();

        final View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        // Bind Butterknife variables
        ButterKnife.bind(this, rootView);

        // Display the loading icon
        mLoadingIndicator.setVisibility(View.VISIBLE);

        // Load the detail and description text
        loadHistoryData();

        // Setup the recyclerview for the pictures
        // Create a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialize the RecyclerView adapter, PictureAdapter
        mAdapter = new PictureAdapter(mContext);

        // Set the adapter
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    private void loadHistoryData(){
        // Initialize Firebase components
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

        // Get a reference to the history node
        DatabaseReference mHistoryDatabaseReference = mFirebaseDatabase.getReference("yola/history");

        // Setup the Firebase database event listener
        ChildEventListener mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                History history = dataSnapshot.getValue(History.class);
                mSummary.setText(history.getSummary());
                mDetail.setText(history.getDetail());

                // Hide loading icon
                mLoadingIndicator.setVisibility(View.INVISIBLE);

                // Add pictures to the recyclerview
                mAdapter.setPictures(history.getPictures());

                // Display static content
                mSummaryHeading.setVisibility(View.VISIBLE);
                mDivider1.setVisibility(View.VISIBLE);
                mDetailHeading.setVisibility(View.VISIBLE);
                mDivider2.setVisibility(View.VISIBLE);
                mAttribution.setVisibility(View.VISIBLE);
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
        mHistoryDatabaseReference.addChildEventListener(mChildEventListener);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARTICLE_SCROLL_POSITION, mScrollView.getScrollY());
    }

   @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(savedInstanceState != null)
        {
            final int positionY = savedInstanceState.getInt(ARTICLE_SCROLL_POSITION);
            if(positionY != 0)
                mScrollView.post(new Runnable() {
                    public void run() {
                        mScrollView.scrollTo(0, positionY);
                    }
                });
        }
    }
}
