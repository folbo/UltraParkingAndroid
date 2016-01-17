package net.silver.ultra.ultraandroid;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import net.silver.ultra.ultraandroid.parking.ParkingReservationActivity_;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements OnMapReadyCallback {

    @Bind(R.id.fab) FloatingActionButton fab;
    GoogleMap map;
    Location myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //fab.hide();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @OnClick(R.id.fab)
    public void OnFabClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        ParkingReservationActivity_.intent(this).start();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMyLocationEnabled(true);

        LatLng warszawa = new LatLng(52.234, 21.001);
        //map.animateCamera();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(warszawa, 12.0f));
                /*CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                        .zoom(14)                   // Sets the zoom
                                //.bearing(90)                // Sets the orientation of the camera to east
                                //.tilt(40)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/

    }

    private void centerMapOnMyLocation() {

    }
}
