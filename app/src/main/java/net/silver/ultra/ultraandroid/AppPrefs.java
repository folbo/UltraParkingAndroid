package net.silver.ultra.ultraandroid;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by folbo on 2016-01-15.
 */

@SharedPref
public interface AppPrefs {

    String GetAuthCookieValue();
    String GetUserEmail();
}
