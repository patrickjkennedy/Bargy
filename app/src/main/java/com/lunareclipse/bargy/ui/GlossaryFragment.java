package com.lunareclipse.bargy.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.lunareclipse.bargy.R;
import com.lunareclipse.bargy.data.GlossaryAdapter;
import com.lunareclipse.bargy.model.Phrase;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    // Search Button
    @BindView(R.id.ib_search) ImageButton mSearchImageButton;

    // Search EditText
    @BindView(R.id.et_search) EditText mSearchEditText;

    // Key for Recycler Layout
    private static final String BUNDLE_RECYCLER_LAYOUT = "GlossaryFragment.recycler.layout";

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mGlossaryDatabaseReference;
    private ChildEventListener mChildEventListener;

    public GlossaryFragment() {
        // Required empty public constructor
    }

    // Inflates the RecyclerView
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        // Context
        mContext = getActivity();

        final View rootView = inflater.inflate(R.layout.fragment_glossary, container, false);

        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mGlossaryDatabaseReference = mFirebaseDatabase.getReference().child("yola");

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

        // Load the recyclerview with the glossary data from Firebase
        loadGlossaryData();

        // Search button click listener
        mSearchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchGlossary();
                returnFocus();
            }
        });

        // EditText event action listener
        mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    searchGlossary();
                    returnFocus();
                    return true;
                }
                return false;
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

    private void loadGlossaryData(){
        // Get a reference to the child node of the language
        mGlossaryDatabaseReference = mFirebaseDatabase.getReference().child("yola");

        // Setup the Firebase database event listener
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Phrase phrase = dataSnapshot.getValue(Phrase.class);
                mAdapter.add(phrase);
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
        mGlossaryDatabaseReference.addChildEventListener(mChildEventListener);
    }

    private void searchGlossary(){

        // Remove data from the recyclerview
        mAdapter.clear();

        // Get query string
        String query = mSearchEditText.getText().toString();

        if (!query.isEmpty()){
            // Phrases are capitalized in the database
            String capitalizedQuery = query.substring(0, 1).toUpperCase() + query.substring(1);
            // Do search
            mGlossaryDatabaseReference
                    .orderByChild("translated_phrase")
                    .startAt(capitalizedQuery)
                    .endAt(capitalizedQuery + "\uf8ff")
                    .addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            Phrase phrase = dataSnapshot.getValue(Phrase.class);

                            // Add phrases to the recyclerview
                            mAdapter.add(phrase);
                            mAdapter.notifyDataSetChanged();
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
                    });
        } else{
            loadGlossaryData();
        }
    }

    private void returnFocus(){
        // Hide keyboard and return focus
        mRecyclerView.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
