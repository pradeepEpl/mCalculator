package ps.android.mcalculator;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {

    private static SharedPreferences preferences;
    public static Preference pref;

    public static Preference getPreference(Context context) {
        preferences = context.getSharedPreferences("Merit", 0);
        return new Preference();
    }

    public  void saveUser(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public  String getUser(String key) {
        return preferences.getString(key, null);
    }
}
