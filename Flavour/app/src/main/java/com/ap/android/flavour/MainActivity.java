package com.ap.android.flavour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    TextView textView1, textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //textView1 = findViewById(R.id.textView1);
        //textView2 = findViewById(R.id.textView2);
        //this.setProperty();
        startActivity(new Intent(this, com.ap.android.myapplication.MainActivity.class));
        finish();

    }

    private void setProperty() {
        try {
            InputStream rawResource = getResources().openRawResource(R.raw.config);
            Properties properties = new Properties();
            properties.load(rawResource);
            String s = properties.getProperty("url");
            textView1.setText("Client : " + getString(R.string.client));
            textView2.setText("Server : " + s);
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Unable to find the config file: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "Failed to open config file.");
        }
    }
}
