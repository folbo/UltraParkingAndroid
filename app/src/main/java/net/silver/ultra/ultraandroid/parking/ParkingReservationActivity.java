package net.silver.ultra.ultraandroid.parking;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import net.silver.ultra.ultraandroid.BaseActivity;
import net.silver.ultra.ultraandroid.MyApp;
import net.silver.ultra.ultraandroid.R;
import net.silver.ultra.ultraandroid.parking.event.ParkingReservedEvent;
import net.silver.ultra.ultraandroid.parking.model.ReserveParams;
import net.silver.ultra.ultraandroid.parking.model.ReserveReturns;
import net.silver.ultra.ultraandroid.util.RestManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.UUID;

@EActivity(R.layout.activity_parking_reservation)
public class ParkingReservationActivity extends BaseActivity {

    @App
    MyApp app;

    @Bean
    RestManager restManager; 

    @ViewById(R.id.example)
    EditText exampleEditText;

    @ViewById(R.id.reservation_parking_name)
    TextView parkingName;

    @Extra("e_parkingId")
    UUID e_parkingId;
    @Extra("e_parkingName")
    String e_parkingName;

    @AfterViews
    void initView() {
        parkingName.setText(e_parkingName);
    }

    @Click(R.id.reservationButton)
    void reservationButtonClicked() {
        //musisz przed tym zwalidowac czy uzytjkownik wypelnil co mial wypelnic
        reserveParking(e_parkingId);
    }


    @Background
    void reserveParking(final UUID createRequest) {
        ReserveReturns parkingPlace = restManager.getParkingRestService().reserveParking(new ReserveParams(createRequest));

        if(parkingPlace != null)
            app.getBus().post(new ParkingReservedEvent(parkingPlace));

        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app.getBus().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        app.getBus().unregister(this);
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
