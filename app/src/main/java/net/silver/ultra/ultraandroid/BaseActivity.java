package net.silver.ultra.ultraandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import net.silver.ultra.ultraandroid.Authentication.LoginActivity_;
import net.silver.ultra.ultraandroid.Authentication.event.UserLoggedIn;
import net.silver.ultra.ultraandroid.Authentication.event.UserLoggedOut;
import net.silver.ultra.ultraandroid.util.RestManager;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_base)
public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @App
    protected MyApp app;

    @Pref
    protected AppPrefs_ prefs;

    @Bean
    protected RestManager restManager;

    private NavigationView navigationView;
    private DrawerLayout fullLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private int selectedNavItemId;


    @Override
    public void setContentView(int layoutResID)
    {
        /**
         * This is going to be our actual root layout.
         */
        fullLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        /**
         * {@link FrameLayout} to inflate the child's view. We could also use a {@link android.view.ViewStub}
         */
        FrameLayout activityContainer = (FrameLayout) fullLayout.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);

        /**
         * Note that we don't pass the child's layoutId to the parent,
         * instead we pass it our inflated layout.
         */
        super.setContentView(fullLayout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigationView);

        if (useToolbar())
        {
            setSupportActionBar(toolbar);
        }
        else
        {
            toolbar.setVisibility(View.GONE);
        }

        setUpNavView();
    }

    /**
     * Helper method that can be used by child classes to
     * specify that they don't want a {@link Toolbar}
     * @return true
     */
    protected boolean useToolbar()
    {
        return true;
    }

    private void setLoginButtons(boolean loggedIn)
    {
        Menu menu = navigationView.getMenu();
        MenuItem nav_login = menu.findItem(R.id.nav_login);
        MenuItem nav_logout = menu.findItem(R.id.nav_logout);

            nav_login.setVisible(!loggedIn);
            nav_logout.setVisible(loggedIn);
    }

    protected void setUpNavView()
    {
        navigationView.setNavigationItemSelectedListener(this);

        setLoginButtons(restManager.IsLoggedIn());

        if( useDrawerToggle()) { // use the hamburger menu
            drawerToggle = new ActionBarDrawerToggle(this, fullLayout, toolbar,
                    R.string.nav_drawer_opened,
                    R.string.nav_drawer_closed);

            fullLayout.setDrawerListener(drawerToggle);
            drawerToggle.syncState();
        } else if(useToolbar() && getSupportActionBar() != null) {
            // Use home/back button instead
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeAsUpIndicator(getResources()
             //       .getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        }
    }

    /**
     * Helper method to allow child classes to opt-out of having the
     * hamburger menu.
     * @return
     */
    protected boolean useDrawerToggle()
    {
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        fullLayout.closeDrawer(GravityCompat.START);
        selectedNavItemId = menuItem.getItemId();

        return onOptionsItemSelected(menuItem);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.nav_map:
                //startActivity(new Intent(this, MainActivity.class));
                return true;

            case R.id.nav_login:
                LoginActivity_.intent(this).start();
                //startActivity(new Intent(this, LoginActivity_.class));
                return true;

            case R.id.nav_logout:
                logout();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Background
    protected void logout(){
        restManager.Logout();
    }

    @Override
    public void onBackPressed() {
        if (fullLayout.isDrawerOpen(GravityCompat.START)) {
            fullLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app.getBus().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        app.getBus().unregister(this);
    }


    @Subscribe
    public void onUserLoggedInEvent(UserLoggedIn event) {
        String message = String.format("zalogowano");
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();

        setLoginButtons(true);
    }

    @Subscribe
    public void onUserLoggedOutEvent(UserLoggedOut event) {
        String message = String.format("wylogowano");
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();

        setLoginButtons(false);

    }
}
