package com.newforce.myapp.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.newforce.myapp.R;
import com.newforce.myapp.model.ManagerLocation;
import com.newforce.myapp.model.data.Region;

import java.util.List;

public class RegionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private static final String LOG_TAG = "AppLogger";
    private static final String ACTIVITY_TAG = RegionActivity.class.getSimpleName();

    private ManagerLocation managerLocation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.label_regions);
        setContentView(R.layout.activity_region);

        Log.i(LOG_TAG, "onCreate: " + ACTIVITY_TAG);

        managerLocation = new ManagerLocation(this);
        setSpinnerRegion();
    }

    public void onClickRegion(View view) {
        //setSpinnerRegion();
    }


    private void setSpinnerRegion(){
        Spinner spinner = findViewById(R.id.region_spinner);

        List<String> regions = managerLocation.getListStringRegion();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, regions);

        //Set layout when the list of choice appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position >= 0){
            TextView regionNameTextView = findViewById(R.id.region_name);
            TextView regionIdTextView = findViewById(R.id.region_id);

            Region region = managerLocation.getRegion(position + 1);
            setSpinnerComuna(position + 1);

            if(region != null){
                regionIdTextView.setText(String.valueOf(region.getRegionId()));
                regionNameTextView.setText(region.getName());
            }else{
                regionNameTextView.setText(R.string.no_data);
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setSpinnerComuna(int regionId){
        Spinner spinner = findViewById(R.id.comuna_spinner);

        List<String> comunas = managerLocation.getListStringComunaByRegionId(regionId);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, comunas);

        //Set layout when the list of choice appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

}
