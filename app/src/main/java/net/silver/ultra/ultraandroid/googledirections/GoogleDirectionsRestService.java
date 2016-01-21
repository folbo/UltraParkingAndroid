package net.silver.ultra.ultraandroid.googledirections;

import net.silver.ultra.ultraandroid.parking.model.ParkingModel;
import net.silver.ultra.ultraandroid.parking.model.ReserveParams;
import net.silver.ultra.ultraandroid.parking.model.ReserveReturns;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.RequiresCookie;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.androidannotations.api.rest.RestClientRootUrl;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import retrofit2.http.Body;
import retrofit2.http.Path;

/**
 * Created by folbo on 2016-01-20.
 */

@Rest(rootUrl = "https://maps.googleapis.com/maps/api/directions/", converters = { StringHttpMessageConverter.class })
public interface GoogleDirectionsRestService extends RestClientRootUrl, RestClientErrorHandling {
    @Get("json?origin={o_la},{o_lo}&destination={d_la},{d_lo}&key=AIzaSyA1C14Ql4Qp0A696vYakypXeC1lw16SxfE")
    String getRoute(@Path("o_la") double o_la, @Path("o_lo") double o_lo, @Path("d_la") double d_la, @Path("d_lo") double d_lo);
}
