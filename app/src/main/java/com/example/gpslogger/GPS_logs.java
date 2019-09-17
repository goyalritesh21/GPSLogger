package com.example.gpslogger;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class GPS_logs extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_logs);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        LatLng sydney = new LatLng(-34, 151);
        Intent intent = getIntent();
        ArrayList<LatLng> markersArray = intent.getParcelableArrayListExtra(MainActivity.MARKERS_ARRAY);
        System.out.println("In map");
        if (markersArray == null) {
            return;
        }
        if(!markersArray.isEmpty()){
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markersArray.get(0), 17.0f));
        }
        for(LatLng pos : markersArray){
            System.out.println("Marker added");
            mMap.addMarker(new MarkerOptions().position(pos));
        }
    }
}
