package net.silver.ultra.ultraandroid.parking;

import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author gmatusik
 */
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
        afterCreateIncidentObject(parkingPlace);
    }

    private void afterCreateIncidentObject(ReserveReturns reservation) {
        app.getBus().post(new ParkingReservedEvent(reservation));
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
}
