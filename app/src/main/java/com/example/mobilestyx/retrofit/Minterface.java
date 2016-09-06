package com.example.mobilestyx.retrofit;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by mobilestyx on 24/08/16.
 */

public interface Minterface {
    @GET("/JSONParsingTutorial/jsonActors")
    Call<Example> getUser();
/*
    @GET("users/mobilesiri")
    Call<Pojo> getTopRatedMovies();
∞
    @GET("users/mobilesiri")
    Call<Pojo> getMovieDetails();*/



}