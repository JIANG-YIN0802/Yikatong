package com.example.yikatong.book;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> fgList;
    List<String> list;
    public FragmentAdapter(List<Fragment> fgList, List<String> list, FragmentManager fm){
        super(fm);
        this.fgList = fgList;
        this.list = list;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fgList.get(position);
    }

    @Override
    public int getCount() {
        return fgList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}