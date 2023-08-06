package fr.iavotiana.travel.retrofit;

import java.util.List;

import fr.iavotiana.travel.model.Destination;
import fr.iavotiana.travel.model.Hebergement;
import fr.iavotiana.travel.model.UserModel;
import fr.iavotiana.travel.model.UserResponse;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IMyApi {

    @GET("destination")
    Observable<List<Destination>> getDestination();

    @GET("hebergement")
    Observable<List<Hebergement>> getHebergement(@Header("Authorization") String token);


    @POST("user/signin")
    @FormUrlEncoded
    Observable<UserResponse> loginUser(@Field("email")String email, @Field("password") String password);

    @POST("user/sendToken")
    @FormUrlEncoded
    Observable<ResponseBody> sendToken(@Field("token") String token);

}
