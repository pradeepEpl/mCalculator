package com.ap.android.geofencesample;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;
import java.util.Random;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    String TAG = GeofenceBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            //String errorMessage = GeofenceStatusCodes.getErrorString(geofencingEvent.getErrorCode());
            String errormessage = GeofenceStatusCodes.getStatusCodeString(geofencingEvent.getErrorCode());
            Log.e(TAG, ">> " + errormessage);
            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();
        // Test that the reported transition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

            // Get the geofences that were triggered. A single event can trigger
            // multiple geofences.
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            // Get the transition details as a String.
           /* String geofenceTransitionDetails = getGeofenceTransitionDetails(
                    this,
                    geofenceTransition,
                    triggeringGeofences
            );*/

            // Send notification and log the transition details.
            //sendNotification(geofenceTransitionDetails);
            Toast.makeText(context, "Transition in fence", Toast.LENGTH_SHORT).show();
            for (Geofence geofence : triggeringGeofences) {
                Log.d(TAG, "Applied Fence >> " + geofence.getRequestId());

            }

            sendNotification(context);
            //Log.i(TAG, "");
        } else {
            // Log the error.
            Log.d(TAG, "Transition Error >> ");
        }

    }

    public void sendNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "psNotification")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Geofence")
                .setContentText("Transition Occur")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.channel_name);
            String description = "jhkjghkjghkjshdgj"; //getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("psNotification", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(new Random().nextInt(), builder.build());
    }
}
