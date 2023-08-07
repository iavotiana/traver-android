package fr.iavotiana.travel.retrofit;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit instance =null;

    public static  Retrofit getInstance(){
        if(instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl("https://tourism-m1.vercel.app/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return instance;


    }
}
