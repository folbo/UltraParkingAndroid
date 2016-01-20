package net.silver.ultra.ultraandroid;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by folbo on 2016-01-15.
 */
@SharedPref
public interface AppPrefs {

    String GetAuthCookieValue();
    String GetUserEmail();
}
