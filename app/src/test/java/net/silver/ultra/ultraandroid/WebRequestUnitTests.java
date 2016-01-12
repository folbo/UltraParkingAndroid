package net.silver.ultra.ultraandroid;

import junit.framework.Assert;

import net.silver.ultra.ultraandroid.entities.User;
import net.silver.ultra.ultraandroid.web.ApiService;
import net.silver.ultra.ultraandroid.web.requests.LoginRequest;

import org.junit.Test;

import java.util.UUID;

import retrofit2.Call;

import static org.junit.Assert.assertEquals;

/**
 * Created by folbo on 2016-01-12.
 */
public class WebRequestUnitTests {
    @Test
    public void loginRequestTest() throws Exception {
        ApiService api = new ApiService();
        LoginRequest request = new LoginRequest("szympans18@gmail.com", "123456");
        User user = api.userService.login(request).execute().body();
        assertEquals(user.userId, UUID.fromString("78897bda-1c8c-4ebc-bf2f-1c5e9728dc0f"));
    }
}
