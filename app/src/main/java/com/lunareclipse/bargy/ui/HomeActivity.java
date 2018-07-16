package com.lunareclipse.bargy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.lunareclipse.bargy.R;
import android.support.v7.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
