package com.example.task2.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.task2.Fragments.FirstFrag;
import com.example.task2.Fragments.SecondFragment;
import com.example.task2.Fragments.ThirdFragment;


public class FragmentAdapter extends FragmentStateAdapter {


    private final Context mContext;



    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Context mContext) {
        super(fragmentManager, lifecycle);
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new FirstFrag();
        } else if (position == 1){
            return new SecondFragment();
        } else{
            return new ThirdFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}