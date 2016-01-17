package net.silver.ultra.ultraandroid;

import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import net.silver.ultra.ultraandroid.parking.ParkingReservationActivity_;
import net.silver.ultra.ultraandroid.parking.model.GetAllParkingsReturns;
import net.silver.ultra.ultraandroid.util.RestManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    @App
    MyApp app;

    @Bean
    RestManager restManager;

    @ViewById(R.id.fab) protected FloatingActionButton fab;
    protected GoogleMap map;
    protected Location myLocation;
    protected List<ParkingMarker> parkings = new ArrayList<>();
    private HashMap<Marker, UUID> MarkerParkingMap = new HashMap<Marker, UUID>();

    @AfterViews
    void initViews(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fab.hide();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

    }

    @Click(R.id.fab)
    public void OnFabClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        ParkingReservationActivity_.intent(this).start();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMarkerClickListener(this);

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


        getAllParkings();
    }
    @Background
    protected void getAllParkings(){
        GetAllParkingsReturns[] all = restManager.getParkingRestService().getAll();
        for(GetAllParkingsReturns parking : all){
            ParkingMarker marker = new ParkingMarker(parking.getId(), new MarkerOptions().position(new LatLng(parking.getLocationLatitude(), parking.getLocationLongitude())));
            parkings.add(marker);
        }

        updateMarkers();
    }

    @UiThread
    protected void updateMarkers(){
        for(ParkingMarker marker : parkings){
            Marker m = map.addMarker(marker.getMarkerOptions());
            MarkerParkingMap.put(m, marker.getParkingId());
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        ParkingReservationActivity_.intent(this).parkingId(MarkerParkingMap.get(marker)).start();
        return true;
    }
}

class ParkingMarker {
    UUID parkingId;
    MarkerOptions markerOptions;

    public ParkingMarker(UUID parkingId, MarkerOptions markerOptions){
        this.markerOptions = markerOptions;
        this.parkingId = parkingId;
    }

    public MarkerOptions getMarkerOptions() {
        return markerOptions;
    }

    public UUID getParkingId() {
        return parkingId;
    }

    public MarkerOptions setMarkerOptions(MarkerOptions markerOptions) {
        return this.markerOptions = markerOptions;
    }

    public UUID setParkingId(UUID parkingId) {
        return this.parkingId = parkingId;
    }
}
