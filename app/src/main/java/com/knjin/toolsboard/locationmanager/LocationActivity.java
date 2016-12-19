package com.knjin.toolsboard.locationmanager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.aheadle.toolsboard.R;

public class LocationActivity extends AppCompatActivity {
    Handler mHandler = new Handler();
    TextView tv ;
    LocationManager locationManager;
    String provider;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        tv = (TextView) findViewById(R.id.tv);
       // getLocation();
    }


}
