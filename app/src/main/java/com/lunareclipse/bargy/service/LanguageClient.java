package com.lunareclipse.bargy.service;

import com.lunareclipse.bargy.model.Language;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LanguageClient {

    @GET("languages.json")
    Call<ArrayList<Language>> getLanguages();
}
