package com.lunareclipse.bargy.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lunareclipse.bargy.R;
import com.lunareclipse.bargy.model.Language;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

/*        // Get the language name and update the Activity title on-screen
        Intent intent = getIntent();
        Language language = (Language) intent.getSerializableExtra("language");
        this.setTitle(language.getName());*/
    }
}
