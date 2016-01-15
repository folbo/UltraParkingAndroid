package net.silver.ultra.ultraandroid;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by folbo on 2016-01-15.
 */
public class AppSettings {
    private SharedPreferences prefs;

    public AppSettings(Activity context) {
        prefs = context.getPreferences(Context.MODE_PRIVATE);
    }

    public void setLogin(Boolean login){
        if(login)
            prefs.edit().putString("logged_in", "yes").apply();
        else
            prefs.edit().putString("logged_in", "no").apply();
    }

    public Boolean getLogin() {
        Boolean result = prefs.getString("logged_in", "no").equals("yes");
        System.out.println(result);
        return result;
    }
}
