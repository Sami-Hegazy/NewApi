package com.example.task2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.task2.Adapters.RecycleInfAdapter;
import com.example.task2.Models.NewHeadLines;
import com.example.task2.Utilities.OnFetchDataListener;
import com.example.task2.R;
import com.example.task2.Utilities.RequestManager;

import java.util.List;

public class FirstFrag extends Fragment implements RecycleInfAdapter.ListItemClickListener {

   // List<NewHeadLines> list;
    RecyclerView recyclerView;

    public FirstFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        RequestManager requestManager = new RequestManager(getContext());
        requestManager.getNewsHeadLines(listener);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }


    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(List<NewHeadLines> list, String message) {
            RecycleInfAdapter recycleAdapter = new RecycleInfAdapter(getContext(),headLines -> {} , list );
            recyclerView.setAdapter(recycleAdapter);
        }

        @Override
        public void onError(String message) {

        }
    };


    @Override
    public void OnListItemClickListener(NewHeadLines headLines) {

    }
}