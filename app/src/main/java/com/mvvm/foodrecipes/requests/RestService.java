package com.mvvm.foodrecipes.requests;

import com.mvvm.foodrecipes.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                                                        .baseUrl(Constants.BASE_URL)
                                                        .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = retrofitBuilder.build();

    private static RestApi restEndPoint = retrofit.create(RestApi.class);

    public static RestApi getRestApi(){
        return restEndPoint;
    }
}
