package com.ap.android.navcomponent;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import akndmr.github.io.colorprefutil.ColorPrefUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    int selectedBackgroundColorId;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        preferences = getSharedPreferences("theme", MODE_PRIVATE);
        //SharedPreferences.Editor editor = new SharedPreferences.Editor()
    }

    /*@Override
    public Resources.Theme getTheme() {
        SharedPreferences mSharedPreferences = getSharedPreferences("the", MODE_PRIVATE);
        String the = mSharedPreferences.getString("theme", null);
        Log.d(TAG, the);
        Resources.Theme theme = super.getTheme();
        if ("appB".equals(the)) {
            theme.applyStyle(R.style.AppThemePurple, true);

        } else {
            theme.applyStyle(R.style.AppTheme, true);
        }
        // you could also use a switch if you have many themes that could apply
        return theme;
    }*/
    private void setTheme() {


        SharedPreferences mSharedPreferences = getSharedPreferences("Theme", MODE_PRIVATE);
        int themeSelected = mSharedPreferences.getInt("THEME_SELECTED", R.style.AppTheme);
        ColorPrefUtil.changeThemeStyle(this, themeSelected);
    }
}

