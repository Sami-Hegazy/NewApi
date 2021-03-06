package com.example.task2.ui.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.task2.data.models.NewHeadLines;
import com.example.task2.ui.view.adapters.RecycleInfAdapter;
import com.example.task2.R;
import com.example.task2.ui.viewModels.NewsViewModel;

import java.util.List;

public class FirstFrag extends Fragment{

    RecyclerView recyclerView;
    NewsViewModel newsViewModel;


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
        View view = inflater.inflate(R.layout.fragment, container, false);

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.getPostsByCat("sports");


        recyclerView = view.findViewById(R.id.recycler_view);
        RecycleInfAdapter recycleAdapter = new RecycleInfAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recycleAdapter);

        newsViewModel.listPostMutableLiveData.observe(getActivity(),
                new Observer<List<NewHeadLines>>() {
                    @Override
                    public void onChanged(List<NewHeadLines> headLines) {
                        recycleAdapter.setList(headLines);
                    }
                });

        return view;
    }
}