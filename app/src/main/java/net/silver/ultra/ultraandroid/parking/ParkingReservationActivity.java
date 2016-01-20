package net.silver.ultra.ultraandroid.parking;

import android.view.MenuItem;
import android.widget.TextView;

import net.silver.ultra.ultraandroid.BaseActivity;
import net.silver.ultra.ultraandroid.R;
import net.silver.ultra.ultraandroid.parking.event.ParkingReservedEvent;
import net.silver.ultra.ultraandroid.parking.model.ParkingModel;
import net.silver.ultra.ultraandroid.parking.model.ReserveParams;
import net.silver.ultra.ultraandroid.parking.model.ReserveReturns;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.UUID;

@EActivity(R.layout.activity_parking_reservation)
public class ParkingReservationActivity extends BaseActivity {

    @ViewById(R.id.reservation_parking_name) TextView parkingName;
    @ViewById(R.id.reservation_owner_name) TextView ownerName;
    @ViewById(R.id.free_places_TextView) TextView freePlaces;

    @Extra("e_parkingId") UUID e_parkingId;
    @Extra("e_parkingName") String e_parkingName;
    @Extra("e_parkingTotalPlaces") int e_parkingTotalPlaces;
    @Extra("e_parkingFreePlaces") int e_parkingFreePlaces;
    @Extra("e_parkingOwnerName") String e_parkingOwnerName;

    @AfterViews
    void initView() {
        parkingName.setText(e_parkingName);
        ownerName.setText(e_parkingOwnerName);
        freePlaces.setText(Integer.toString(e_parkingFreePlaces));

        updateParkingData();
    }

    @Click(R.id.reservationButton)
    void reservationButtonClicked() {
        //musisz przed tym zwalidowac czy uzytjkownik wypelnil co mial wypelnic
        reserveParking(e_parkingId);
    }

    @Background
    void updateParkingData(){
        final ParkingModel parking = restManager.getParkingRestService().getOne(e_parkingId.toString());

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                freePlaces.setText(Integer.toString(parking.getFreePlacesCount()));
            }
        });
    }


    @Click(R.id.navigateButton)
    void navigateButtonClicked(){ test();    }

    @Background
    void test(){
        //restManager.getParkingRestService().testPass();
    }


    @Background
    void reserveParking(final UUID createRequest) {
        ReserveReturns parkingPlace = restManager.getParkingRestService().reserveParking(new ReserveParams(createRequest));

        if(parkingPlace != null)
            app.getBus().post(new ParkingReservedEvent(parkingPlace));

        finish();
    }

    @Override
    protected boolean useDrawerToggle(){
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}
