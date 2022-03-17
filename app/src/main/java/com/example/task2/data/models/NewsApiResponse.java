package com.example.task2.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class NewsApiResponse implements Parcelable {
    String status;
    int totalResults;
    List<NewHeadLines> articles;

    protected NewsApiResponse(Parcel in) {
        status = in.readString();
        totalResults = in.readInt();
        articles = in.createTypedArrayList(NewHeadLines.CREATOR);
    }

    public static final Creator<NewsApiResponse> CREATOR = new Creator<NewsApiResponse>() {
        @Override
        public NewsApiResponse createFromParcel(Parcel in) {
            return new NewsApiResponse(in);
        }

        @Override
        public NewsApiResponse[] newArray(int size) {
            return new NewsApiResponse[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewHeadLines> getArticles() {
        return articles;
    }

    public void setArticles(List<NewHeadLines> articles) {
        this.articles = articles;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(status);
        parcel.writeInt(totalResults);
        parcel.writeTypedList(articles);
    }
}
