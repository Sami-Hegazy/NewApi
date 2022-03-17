package com.example.task2.data.remote;
import com.example.task2.data.models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchNews {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private ApiInterFace apiInterface;
    private static FetchNews INSTANCE;

    public FetchNews() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterFace.class);
    }

    public static FetchNews getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new FetchNews();
        }
        return INSTANCE;
    }

    public Call<NewsApiResponse> getPosts(){
        return apiInterface.getPost();
    }
}
