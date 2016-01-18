package net.silver.ultra.ultraandroid;

import android.location.Location;
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
import net.silver.ultra.ultraandroid.parking.model.ParkingModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
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

    @ViewById(R.id.fab) protected FloatingActionButton fab;
    protected GoogleMap map;
    protected Location myLocation;
    protected List<ParkingViewModel> parkings = new ArrayList<>();
    private HashMap<Marker, ParkingViewModel> MarkerParkingMap = new HashMap<Marker, ParkingViewModel>();

    @AfterViews
    void initViews(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Click(R.id.fab)
    public void OnFabClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
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
        parkings.clear();

        ParkingModel[] all = restManager.getParkingRestService().getAll();

        for(ParkingModel parking : all){
            ParkingViewModel model = new ParkingViewModel();
            model.setParkingName(parking.getName());
            model.setOwnerName(parking.getOwnerId().toString());
            model.setFreePlaces(parking.getFreePlacesCount());
            model.setTotalPlaces(parking.getTotalPlacesCount());
            model.setMarkerOptions(new MarkerOptions().position(new LatLng(parking.getLocationLatitude(), parking.getLocationLongitude())));
            model.setParkingId(parking.getId());

            parkings.add(model);
        }

        updateMarkers();
    }

    @UiThread
    protected void updateMarkers(){
        MarkerParkingMap.clear();
        for(ParkingViewModel parking : parkings){
            Marker m = map.addMarker(parking.getMarkerOptions());
            MarkerParkingMap.put(m, parking);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        ParkingViewModel parking = MarkerParkingMap.get(marker);

        ParkingReservationActivity_.intent(this)
                .e_parkingId(parking.getParkingId())
                .e_parkingName(parking.getParkingName())
                .e_parkingFreePlaces(parking.getFreePlaces())
                .e_parkingOwnerName(parking.getOwnerName())
                .start();
        return true;
    }
}

class ParkingViewModel {
    UUID parkingId;
    String parkingName;
    int freePlaces;
    int totalPlaces;
    String ownerName;

    MarkerOptions markerOptions;

    // getters & setters
    public UUID getParkingId() {
        return parkingId;
    }
    public UUID setParkingId(UUID parkingId) {
        return this.parkingId = parkingId;
    }

    public MarkerOptions getMarkerOptions() {
        return markerOptions;
    }
    public MarkerOptions setMarkerOptions(MarkerOptions markerOptions) {
        return this.markerOptions = markerOptions;
    }

    public String getParkingName() {
        return parkingName;
    }
    public String setParkingName(String parkingName) {
        return this.parkingName = parkingName;
    }

    public int getFreePlaces() {
        return freePlaces;
    }
    public int setFreePlaces(int freePlaces) {
        return this.freePlaces = freePlaces;
    }

    public int getTotalPlaces() {
        return totalPlaces;
    }
    public int setTotalPlaces(int totalPlaces) {
        return this.totalPlaces = totalPlaces;
    }

    public String getOwnerName() {
        return ownerName;
    }
    public String setOwnerName(String ownerName) {
        return this.ownerName = ownerName;
    }
}
