package net.silver.ultra.ultraandroid.parking.rest;

import net.silver.ultra.ultraandroid.parking.model.ParkingModel;
import net.silver.ultra.ultraandroid.parking.model.ReserveParams;
import net.silver.ultra.ultraandroid.parking.model.ReserveReturns;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.RequiresCookie;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.androidannotations.api.rest.RestClientRootUrl;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.UUID;

import retrofit2.http.Body;
import retrofit2.http.Path;


/**
 * Created by Sylwekqaz on 15.01.2016.
 */

@Rest(rootUrl = "http://ultra.dev/api",converters = {MappingJackson2HttpMessageConverter.class})
public interface ParkingRestService extends RestClientRootUrl, RestClientErrorHandling {

    @Post("/parking/bookPlace")
    @RequiresCookie({".AspNet.ApplicationCookie"})
    ReserveReturns reserveParking(@Body ReserveParams parkingId);

    @Get("/parking/all")
    ParkingModel[] getAll();

    @Get("/parking/{id}")
    ParkingModel getOne(@Path("id") String id);

    void setCookie(String name, String value);
}
