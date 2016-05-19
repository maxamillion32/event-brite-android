package com.example.demo.eventbritedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.adapter.EventPagerAdapter;
import com.example.demo.eventbritedemo.fragment.CancelledEventListFragment;
import com.example.demo.eventbritedemo.fragment.CompletedEventListFragment;
import com.example.demo.eventbritedemo.fragment.EndedEventListFragment;
import com.example.demo.eventbritedemo.fragment.LiveEventListFragment;
import com.example.demo.eventbritedemo.fragment.SavedEventListFragment;
import com.example.demo.eventbritedemo.fragment.StartedEventListFragment;
import com.example.demo.eventbritedemo.model.PagerModel;

import java.util.ArrayList;
import java.util.List;

public class EventListActivity extends AppCompatActivity {

    private ViewPager viewpager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        initViews();
    }

    private void initViews() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final FloatingActionButton btnCreateEvent =
                (FloatingActionButton) findViewById(R.id.btnCreateEvent);
        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventListActivity.this, CreateNewEventActivity.class));
            }
        });

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpager.setAdapter(getPagerAdapter());
        viewpager.setOffscreenPageLimit(viewpager.getAdapter().getCount() - 1);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((EventPagerAdapter) viewpager.getAdapter()).getItem(position)
                        .onPageSelected(viewpager.getAdapter(), position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        final TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewpager);
    }

    private EventPagerAdapter getPagerAdapter() {
        final List<PagerModel> list = new ArrayList<>();
        list.add(new PagerModel(new LiveEventListFragment(), "Live"));
        list.add(new PagerModel(new StartedEventListFragment(), "Started"));
        list.add(new PagerModel(new SavedEventListFragment(), "Saved"));
        list.add(new PagerModel(new CompletedEventListFragment(), "Completed"));
        list.add(new PagerModel(new EndedEventListFragment(), "Ended"));
        list.add(new PagerModel(new CancelledEventListFragment(), "Cancelled"));
        return new EventPagerAdapter(getFragmentManager(), list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                startActivity(new Intent(this, SearchEventActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
