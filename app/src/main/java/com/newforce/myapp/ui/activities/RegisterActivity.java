package com.newforce.myapp.ui.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.newforce.myapp.R;


public class RegisterActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AppLogger";
    private static final String ACTIVITY_TAG = RegisterActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.label_register);
        setContentView(R.layout.activity_register);

        Log.i(LOG_TAG, "onCreate: " + ACTIVITY_TAG);
    }


}
