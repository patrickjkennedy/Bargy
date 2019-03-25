package com.lunareclipse.bargy.ui;

import android.content.Context;
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
import com.lunareclipse.bargy.data.HistoryAdapter;
import com.lunareclipse.bargy.model.History;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryFragment extends Fragment {

    // RecyclerView
    @BindView(R.id.rv_history) RecyclerView mRecyclerView;

    // HistoryAdapter
    private HistoryAdapter mAdapter;

    // TextView for Error Messaging
    @BindView(R.id.tv_error_message_display_history) TextView mErrorTextView;

    // ProgressBar for Loading
    @BindView(R.id.pb_loading_indicator_history) ProgressBar mLoadingIndicator;

    // Key for Recycler Layout
    private static final String BUNDLE_RECYCLER_LAYOUT = "HistoryFragment.recycler.layout";

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        // Set context
        Context mContext = getActivity();

        final View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        // Bind Butterknife variables
        ButterKnife.bind(this, rootView);

        // Display the loading icon
        mLoadingIndicator.setVisibility(View.VISIBLE);

        // Create a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialize the RecyclerView adapter, HistoryAdapter
        mAdapter = new HistoryAdapter(mContext);

        // Set the adapter
        mRecyclerView.setAdapter(mAdapter);

        // Load data
        loadHistoryData(savedInstanceState);

        return rootView;
    }

    private void loadHistoryData(final Bundle bundle) {
        // Initialize Firebase components
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

        // Get a reference to the history node
        DatabaseReference mHistoryDatabaseReference = mFirebaseDatabase.getReference("yola/history");

        // Setup the Firebase database event listener
        ChildEventListener mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                History history = dataSnapshot.getValue(History.class);
                mAdapter.add(history);
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
        mHistoryDatabaseReference.addChildEventListener(mChildEventListener);
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
