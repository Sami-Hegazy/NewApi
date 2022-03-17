package com.example.task2.Utilities;

import com.example.task2.Models.NewHeadLines;

import java.util.List;

public interface OnFetchDataListener {
    void onFetchData(List<NewHeadLines> list , String message);
    void onError(String message);
}
