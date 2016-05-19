package com.example.demo.eventbritedemo.fragment;

import android.app.Fragment;
import android.support.v4.view.PagerAdapter;

public abstract class PagerFragment extends Fragment {
    public abstract void onPageSelected(PagerAdapter adapter, int position);

    /**
     * method to catch the onBackPress event in the activity
     *
     * @return true to stop the back-press event propagation to activity, false otherwise
     */
    public boolean onBackPress() {
        return false;
    }
}
