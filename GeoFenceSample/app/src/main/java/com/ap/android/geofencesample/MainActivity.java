package com.ap.android.geofencesample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Snapshot;
import androidx.databinding.DataBindingUtil;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ap.android.geofencesample.databinding.ActivityMainBinding;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private GeofencingClient geofencingClient;
    private ArrayList<Geofence> geofenceList = new ArrayList<>();
    private PendingIntent geofencePendingIntent;
    private LocationRequest locationRequest;
    private LocationManager locationManager;
    private double lat;
    private double lng;
    private float radius;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //getCurrentLoaction();
        createGeofence();
        geofencingClient = LocationServices.getGeofencingClient(this);
        binding.set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lat = Double.parseDouble(binding.lat.getText().toString());
                lng = Double.parseDouble(binding.lng.getText().toString());
                //radius = Float.parseFloat(binding.radius.getText().toString());

                addGeofenceClient();
            }
        });
    }

    private void createGeofence() {
        geofenceList.add(new Geofence.Builder()
                .setRequestId("com.ap.android.geofencesample.PSGeoFence")
                .setCircularRegion(26.477694, 80.336276, 200)
                .setExpirationDuration(120000)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build()
        );
        geofenceList.add(new Geofence.Builder()
                .setRequestId("com.ap.android.geofencesample.PSGeoFence")
                .setCircularRegion(26.4316, 80.2983, 200)
                .setExpirationDuration(120000)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build()
        );
        geofenceList.add(new Geofence.Builder()
                .setRequestId("com.ap.android.geofencesample.PSGeoFence")
                .setCircularRegion(26.4675, 80.3165, 200)
                .setExpirationDuration(120000)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build()
        );
    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(geofenceList);
        return builder.build();
    }

    private PendingIntent getGeofencePendingIntent() {
        // Reuse the PendingIntent if we already have it.
        if (geofencePendingIntent != null) {
            return geofencePendingIntent;
        }
        Intent intent = new Intent(this, GeofenceBroadcastReceiver.class);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when
        // calling addGeofences() and removeGeofences().
        geofencePendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return geofencePendingIntent;
    }

    private void addGeofenceClient() {
        geofencingClient.addGeofences(getGeofencingRequest(), getGeofencePendingIntent())
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Exp >> " + e.getMessage(), e);
                    }
                })
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Geofence added Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getCurrentLoaction() {
        binding.loading.setVisibility(View.VISIBLE);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 20000, 100, this);
        } catch (SecurityException e) {
            Log.e(TAG, "Exp >> " + e.getMessage(), e);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        binding.loading.setVisibility(View.GONE);
        lat = location.getLatitude();
        lng = location.getLongitude();
        Log.d(TAG, "Location >> " + lat + " , " + lng);
        binding.lat.setText(String.valueOf(lat));
        binding.lng.setText(String.valueOf(lng));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

