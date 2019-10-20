package cn.example.baselib.view.tabswitchwidget;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lee on 2018/4/3.
 */

public class SlidingFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = null;
    private FragmentManager mFragmentManager;

    public SlidingFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragmentManager = fm;
        mFragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position < mFragmentList.size()) {
            fragment = mFragmentList.get(position);
        } else {
            fragment = mFragmentList.get(0);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }
}
