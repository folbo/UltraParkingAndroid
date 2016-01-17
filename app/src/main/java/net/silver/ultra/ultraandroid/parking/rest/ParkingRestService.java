package net.silver.ultra.ultraandroid.parking.rest;

import net.silver.ultra.ultraandroid.parking.model.GetAllParkingsReturns;
import net.silver.ultra.ultraandroid.parking.model.ReserveParams;
import net.silver.ultra.ultraandroid.parking.model.ReserveReturns;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.androidannotations.api.rest.RestClientRootUrl;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import retrofit2.http.Body;


/**
 * @author aturski
 */
@Rest(rootUrl = "http://ultra.dev/api",converters = {MappingJackson2HttpMessageConverter.class})
public interface ParkingRestService extends RestClientRootUrl, RestClientErrorHandling {

    @Post("/parking/bookPlace")
    ReserveReturns reserveParking(@Body ReserveParams parkingId);

    @Get("/parking/all")
    GetAllParkingsReturns[] getAll();
}
