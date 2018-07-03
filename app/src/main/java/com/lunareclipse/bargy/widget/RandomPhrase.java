package com.lunareclipse.bargy.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.lunareclipse.bargy.model.Phrase;
import java.util.HashMap;
import java.util.Random;

public class RandomPhrase {

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mGlossaryDatabaseReference;
    private ChildEventListener mChildEventListener;

    // Hashmap for language glossary counts
    HashMap<String, Integer> glossaryMaxCounts = new HashMap<>();

    // Random phrase
    private Phrase mPhrase;

    public RandomPhrase(){
        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();

    }

    public Phrase getRandomPhrase(String language){
        // Return phrase
        mPhrase = new Phrase();

        // Get a reference to the glossary node of the language
        String languageGlossary = language + "/glossary/";
        mGlossaryDatabaseReference = mFirebaseDatabase.getReference(languageGlossary);

        // Get a random number between 0 and the max specified for that language in the hashmap;
        Random rand = new Random();

        // Create hashmap for max counts
        initMaxGlossary();

        // Get a random key
        int max = glossaryMaxCounts.get(language);
        int rk = rand.nextInt(max);
        String key = Integer.toString(rk);

        // Retrieve the key from the database
        mGlossaryDatabaseReference
                .orderByKey()
                .startAt(key)
                .limitToFirst(1)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Phrase phrase = dataSnapshot.getValue(Phrase.class);
                        Log.d("WIDGET", phrase.toString());
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
        return mPhrase;
    }

    private void initMaxGlossary(){
        glossaryMaxCounts.put("yola", 556);
    }

}
