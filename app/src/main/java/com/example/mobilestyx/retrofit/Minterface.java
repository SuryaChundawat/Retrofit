package com.example.mobilestyx.retrofit;



import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mobilestyx on 24/08/16.
 */

public interface Minterface {
    @GET("/JSONParsingTutorial/jsonActors")
    Call<Example> getUser();  //call <Example> is a like model so you can access this contain in Response.body();
/*
    @GET("users/mobilesiri")
    Call<Pojo> getTopRatedMovies();
∞
    @GET("users/mobilesiri")
    Call<Pojo> getMovieDetails();*/



}