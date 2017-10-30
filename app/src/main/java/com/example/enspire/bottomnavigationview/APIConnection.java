package com.example.enspire.bottomnavigationview;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by enspire on 9/12/2017.
 */

public class APIConnection {
    private static final String BaseUrl="http://127.0.0.1/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
