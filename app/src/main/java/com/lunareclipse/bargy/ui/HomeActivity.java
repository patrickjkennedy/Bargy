package com.lunareclipse.bargy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lunareclipse.bargy.R;
import com.lunareclipse.bargy.model.Language;
import com.lunareclipse.bargy.service.LanguageClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

                Log.d("HomeActivity", "Response: " + response.body());

            }

            @Override
            public void onFailure(Call<ArrayList<Language>> call, Throwable t) {

            }
        });
    }
}
