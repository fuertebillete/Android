package umlaut.android;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Gabriel Rojas on 19/7/2016.
 */
public interface RequestInterface {

    @POST("campus/")//"campus_android/" // "campus/"
    Call<ServerResponse> operation(@Body ServerRequest request);

}
