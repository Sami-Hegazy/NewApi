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
import com.example.task2.R;

import java.util.ArrayList;
import java.util.List;


public class ThirdFragment extends Fragment {

    List<NewHeadLines> list = new ArrayList<>();

    public ThirdFragment() {
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

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        RecycleInfAdapter recycleAdapter = new RecycleInfAdapter(getContext(), clickItemIndex -> {} , list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(recycleAdapter);
        return view;
    }
}