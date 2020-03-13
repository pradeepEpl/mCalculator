package com.ap.android.mlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class LibActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib);
        TextView textView = findViewById(R.id.textView);
        textView.setText(getString(R.string.lib_text_msg));
    }
}
