package com.example.task2.Utilities;

import android.content.Context;
import android.widget.Toast;

import com.example.task2.Models.NewsApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    Context ctx;

    public RequestManager(Context ctx)   {
        this.ctx = ctx;
    }


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    public void getNewsHeadLines(OnFetchDataListener listener){
        ApiInterFace apiInterFace = retrofit.create(ApiInterFace.class);
        Call<NewsApiResponse> call = apiInterFace.getPost();
        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(ctx, "Error", Toast.LENGTH_SHORT).show();
                    }
                    listener.onFetchData(response.body().getArticles() , response.message());
                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    listener.onError("Request Failed");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

