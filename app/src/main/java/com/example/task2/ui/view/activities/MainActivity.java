package com.example.task2.ui.view.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.task2.R;
import com.example.task2.data.models.NewHeadLines;
import com.example.task2.ui.view.adapters.FragmentAdapter;
import com.example.task2.ui.view.adapters.TopViewPagerAdapter;
import com.example.task2.ui.viewModels.NewsViewModel;
import com.example.task2.utilities.MockUps;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TopViewPagerAdapter topViewPagerAdapter;
    private final Handler handler = new Handler();
    private ViewPager2 topViewPager;
    private int currentPage = 0;
    private Timer timer = new Timer(); ;
    private final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    private final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    TabLayout mainTab;


    private void viewInit() {
        topViewPager = findViewById(R.id.intro_pager);
        TabLayout tabLayout = findViewById(R.id.dot_tab_layout);
        ViewPager2 mainViewPager = findViewById(R.id.main_view_pager);
        mainTab = findViewById(R.id.main_tab_layout);

        topViewPagerAdapter = new TopViewPagerAdapter(this);
        topViewPager.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        topViewPager.setAdapter(topViewPagerAdapter);

        new TabLayoutMediator(tabLayout, topViewPager,
                (tab, position) -> tab.select()
        ).attach();

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle(), this);
        mainViewPager.setAdapter(fragmentAdapter);

        new TabLayoutMediator(mainTab, mainViewPager,
                (tab, position) -> tab.setText(getPageTitle(this, position))
        ).attach();
        setupTabIcons();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewInit();

        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.getPosts();

        newsViewModel.listPostMutableLiveData.observe(this, new Observer<List<NewHeadLines>>() {
            @Override
            public void onChanged(List<NewHeadLines> list) {
                topViewPagerAdapter.setList(list);
            }
        });

        /*After setting the adapter use the timer */
        final Runnable Update = () -> {
            if (currentPage == 3) {
                currentPage = 0;
            }
            topViewPager.setCurrentItem(currentPage++, true);
        };

       // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);




    }

    @Override
    protected void onDestroy() {
        timer = null;
        super.onDestroy();
    }

    public String getPageTitle(Context mContext, int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_first);
        } else if (position == 1) {
            return mContext.getString(R.string.category_second);
        } else {
            return mContext.getString(R.string.category_third);
        }
    }

    private void setupTabIcons() {
        int[] icons = MockUps.getTabIcons();
        mainTab.getTabAt(0).setIcon(icons[0]);
        mainTab.getTabAt(1).setIcon(icons[1]);
        mainTab.getTabAt(2).setIcon(icons[2]);
    }

}