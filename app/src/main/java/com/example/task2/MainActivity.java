package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.task2.Adapters.FragmentAdapter;
import com.example.task2.Adapters.TopViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    TabLayout mainTab;
    private int[] tabIcons = {
            R.drawable.ic_favorite,
            R.drawable.ic_alert,
            R.drawable.ic_shuttle
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 topViewPager = findViewById(R.id.intro_pager);
        topViewPager.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        TopViewPagerAdapter topViewPagerAdapter = new TopViewPagerAdapter(this);
        topViewPager.setAdapter(topViewPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.dot_tab_layout);
        new TabLayoutMediator(tabLayout, topViewPager,
                (tab, position) -> tab.select()
        ).attach();


        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == 3) {
                currentPage = 0;
            }
            topViewPager.setCurrentItem(currentPage++, true);
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        ViewPager2 mainViewPager = findViewById(R.id.main_view_pager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager() , getLifecycle(),this);
        mainViewPager.setAdapter(fragmentAdapter);

        mainTab = findViewById(R.id.main_tab_layout);

        new TabLayoutMediator(mainTab, mainViewPager,
                (tab, position) -> tab.setText(getPageTitle(this,position))
        ).attach();
        setupTabIcons();

    }

    @Override
    protected void onDestroy() {
        timer = null;
        super.onDestroy();
    }

    public String getPageTitle(Context mContext , int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_first);
        } else if (position == 1) {
            return mContext.getString(R.string.category_second);
        } else {
            return mContext.getString(R.string.category_third);
        }
    }

    private void setupTabIcons() {
        mainTab.getTabAt(0).setIcon(tabIcons[0]);
        mainTab.getTabAt(1).setIcon(tabIcons[1]);
        mainTab.getTabAt(2).setIcon(tabIcons[2]);
    }

}