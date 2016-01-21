package net.silver.ultra.ultraandroid;

import android.graphics.Color;
import android.location.Location;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import net.silver.ultra.ultraandroid.parking.ParkingReservationActivity_;
import net.silver.ultra.ultraandroid.parking.model.ParkingModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONObject;

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
    private double nearestParkingLongitude;
    private double nearestParkingLatitude;
    Polyline routePolyline;


    @AfterViews
    void initViews(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Click(R.id.fab)
    public void OnFabClick(View view) {
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT).setAction("Action", null).show();

        //get nearest parking with free places

        if(myLocation == null) return;
        if(parkings == null) return;

        double min = 10000;
        for(Marker m : MarkerParkingMap.keySet())
        {
            if(MarkerParkingMap.get(m).getFreePlaces() == 0) continue;

            double dist = distFrom(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()), m.getPosition());
            if( dist < min ) {
                nearestParkingLatitude = m.getPosition().latitude;
                nearestParkingLongitude = m.getPosition().longitude;

                min = dist;
            }
        }

        if(nearestParkingLatitude != 0 && nearestParkingLongitude != 0) {
            testDirections();
        }
    }

    @Background
    void testDirections(){
            if (myLocation == null) return;

            String route = restManager.getDirectionsRestService().getRoute(myLocation.getLatitude(), myLocation.getLongitude(), nearestParkingLatitude, nearestParkingLongitude);

            try {
                JSONObject result = new JSONObject(route);
                JSONArray routes = result.getJSONArray("routes");

                long distanceForSegment = routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("distance").getInt("value");

                JSONArray steps = routes.getJSONObject(0).getJSONArray("legs")
                        .getJSONObject(0).getJSONArray("steps");

                final List<LatLng> lines = new ArrayList<LatLng>();

                for (int i = 0; i < steps.length(); i++) {
                    String polyline = steps.getJSONObject(i).getJSONObject("polyline").getString("points");

                    for (LatLng p : decodePolyline(polyline)) {
                        lines.add(p);
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(routePolyline != null)
                            routePolyline.remove();
                        routePolyline = map.addPolyline(new PolylineOptions().addAll(lines).width(3).color(Color.RED));
                    }
                });
            } catch (Exception e) {
            }

    }

    private List<LatLng> decodePolyline(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();

        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((double) lat / 1E5, (double) lng / 1E5);
            poly.add(p);
        }

        return poly;
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


        map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                myLocation = location;
            }
        });
    }

    @Background
    protected void getAllParkings(){
        parkings.clear();

        ParkingModel[] all = restManager.getParkingRestService().getAll();

        if(all == null) return;

        for(ParkingModel parking : all){
            ParkingViewModel model = new ParkingViewModel();
            model.setParkingName(parking.getName());
            model.setOwnerName(parking.getOwnerName());
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

    public double distFrom (LatLng loc1, LatLng loc2 )
    {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(loc2.latitude - loc1.latitude);
        double dLng = Math.toRadians(loc2.longitude - loc1.longitude);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(loc1.latitude)) * Math.cos(Math.toRadians(loc2.latitude)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        int meterConversion = 1609;

        return new Double(dist * meterConversion).doubleValue();
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
