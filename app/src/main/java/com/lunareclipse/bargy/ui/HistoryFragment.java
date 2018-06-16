package com.lunareclipse.bargy.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lunareclipse.bargy.R;
import com.lunareclipse.bargy.data.LanguagesAdapter;
import com.lunareclipse.bargy.model.History;
import com.lunareclipse.bargy.model.Language;
import com.lunareclipse.bargy.service.LanguageClient;

import org.w3c.dom.Text;

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

    // Views for content from FRD
    @BindView(R.id.tv_summary) TextView mSummary;
    @BindView(R.id.tv_detail) TextView mDetail;
    @BindView(R.id.tv_summary_heading) TextView mSummaryHeading;
    @BindView(R.id.tv_detail_heading) TextView mDetailHeading;
    @BindView(R.id.tv_attribution) TextView mAttribution;
    @BindView(R.id.v_hdivider) View mDivider;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mHistoryDatabaseReference;
    private ChildEventListener mChildEventListener;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        // Context
        mContext = getActivity();

        final View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        // Bind Butterknife variables
        ButterKnife.bind(this, rootView);

        // Display the loading icon
        mLoadingIndicator.setVisibility(View.VISIBLE);

        // Load the detail and description text
        loadHistoryData();

        return rootView;
    }

    private void loadHistoryData(){
        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        // Get a reference to the history node
        mHistoryDatabaseReference = mFirebaseDatabase.getReference("yola/history");

        // Setup the Firebase database event listener
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                History history = dataSnapshot.getValue(History.class);
                System.out.println(history.getDetail());
                mSummary.setText(history.getSummary());
                mDetail.setText(history.getDetail());
                //Hide loading icon
                mLoadingIndicator.setVisibility(View.INVISIBLE);
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
        // Display static content
        mSummaryHeading.setVisibility(View.VISIBLE);
        mDivider.setVisibility(View.VISIBLE);
        mDetailHeading.setVisibility(View.VISIBLE);
        mAttribution.setVisibility(View.VISIBLE);
    }
}
