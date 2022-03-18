package com.example.task2.data.remote;
import com.example.task2.data.models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterFace {

//    @GET("top-headlines?category=sports&apiKey=aeb8da21db0e425c928bbcaff4341209")
//    Call<NewsApiResponse> getPost();

    @GET("top-headlines?{}&apiKey=aeb8da21db0e425c928bbcaff4341209")
    Call<NewsApiResponse> getPostByCategory(@Query("category") String category);
}

