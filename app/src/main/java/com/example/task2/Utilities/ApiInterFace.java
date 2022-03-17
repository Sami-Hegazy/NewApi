package com.example.task2.Utilities;

import com.example.task2.Models.NewsApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterFace {


    @GET("top-headlines?category=science&apiKey=aeb8da21db0e425c928bbcaff4341209")
    Call<NewsApiResponse> getPost();
}

