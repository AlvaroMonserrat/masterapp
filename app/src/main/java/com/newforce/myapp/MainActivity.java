package com.newforce.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.newforce.myapp.databinding.ActivityMainBinding;
import com.newforce.myapp.model.data.TemperatureData;
import com.newforce.myapp.ui.HomeActivity;
import com.newforce.myapp.ui.activities.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AppLogger";
    private static final String ACTIVITY_TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;
    private TemperatureData tempData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        tempData = new TemperatureData();

        setRoomAndTemperature("Game Room", 18);
        Log.i(LOG_TAG, "onCreate: " + ACTIVITY_TAG) ;
    }

    public void onClickRoom(View view){
        setRoomAndTemperature("Main Room", 21);
        Log.i(LOG_TAG, "onClickRoom: " + ACTIVITY_TAG);
    }

    public void onClickKitchen(View view){
        setRoomAndTemperature("Kitchen", 29);
        Log.i(LOG_TAG, "onClickKitchen" + ACTIVITY_TAG);
    }

    public void onClickRegister(View view){
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
        Log.i(LOG_TAG, "onClickRegister: " + ACTIVITY_TAG);
    }

    public void onClickHome(View view){
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
        Log.i(LOG_TAG, "onClickHome: " + ACTIVITY_TAG);
    }

    private void setRoomAndTemperature(String nameRoom, int tempCelsius){
        tempData.setLocation(nameRoom);
        tempData.setTempCelsius(tempCelsius);
        binding.setTemp(tempData);
    }

}
