package com.example.task2.data.remote;
import com.example.task2.data.models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterFace {


    @GET("top-headlines?category=science&apiKey=aeb8da21db0e425c928bbcaff4341209")
    Call<NewsApiResponse> getPost();

}

