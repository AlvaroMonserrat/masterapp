package com.newforce.myapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.newforce.myapp.R;
import com.newforce.myapp.auth.AuthActivity;

public class AccountActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AppLogger";
    private static final String ACTIVITY_TAG = AccountActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Log.i(LOG_TAG, "onCreate: " + ACTIVITY_TAG);
    }

    public void onClickLogout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent loginIntent = new Intent(this, AuthActivity.class);
        startActivity(loginIntent);
        finish();
    }
}
