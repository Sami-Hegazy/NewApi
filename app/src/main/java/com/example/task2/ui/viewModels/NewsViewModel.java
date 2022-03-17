package com.example.task2.ui.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.task2.data.models.NewHeadLines;
import com.example.task2.data.models.NewsApiResponse;
import com.example.task2.data.remote.FetchNews;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {

    public MutableLiveData<List<NewHeadLines>> listPostMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> posts = new MutableLiveData<>();

    public void getPosts(){
        FetchNews.getINSTANCE().getPosts().enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                listPostMutableLiveData.setValue(response.body().getArticles());
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                posts.setValue("Error");
            }
        });
    }

}
