package com.pablojc.marvelapp.ui.extensions

import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

fun initViewPagerWithTabs(viewPager: ViewPager, tabLayout: TabLayout, pagerAdapter: FragmentPagerAdapter){
    viewPager.adapter = pagerAdapter
    tabLayout.setupWithViewPager(viewPager)
}