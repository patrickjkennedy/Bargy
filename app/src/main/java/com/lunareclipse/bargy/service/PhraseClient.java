package com.lunareclipse.bargy.service;

import com.lunareclipse.bargy.model.Phrase;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PhraseClient {

    @GET("glossary.json")
    Call<ArrayList<Phrase>> getPhrases();
}
