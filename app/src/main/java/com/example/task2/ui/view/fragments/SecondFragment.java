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

import com.example.task2.ui.view.adapters.RecycleInfAdapter;
import com.example.task2.data.models.NewHeadLines;
import com.example.task2.ui.viewModels.NewsViewModel;
import com.example.task2.R;

import java.util.List;

public class SecondFragment extends Fragment {

    RecyclerView recyclerView;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        NewsViewModel newsViewModel = new ViewModelProvider(getActivity()).get(NewsViewModel.class);
        newsViewModel.getPostsByCat("science");

        recyclerView = view.findViewById(R.id.recycler_view);
        RecycleInfAdapter recycleAdapter = new RecycleInfAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recycleAdapter);

        newsViewModel.listPostMutableLiveData.observe(getActivity(),
                new Observer<List<NewHeadLines>>() {
                    @Override
                    public void onChanged(List<NewHeadLines> list) {
                        recycleAdapter.setList(list);
                    }
                });

        return view;
    }

}