package com.example.enspire.bottomnavigationview.service;

import com.example.enspire.bottomnavigationview.model.Organization;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by enspire on 9/12/2017.
 */

public interface APIService {
    @GET("test5/index.php")
    Call<List<Organization>> getOrganization(@Query("uid") String uid);

}
