package com.example.postsmvvmretrofit.retrofit;

import com.example.postsmvvmretrofit.model.ResultModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("posts")
    Call<List<ResultModel>>getPosts();
}
