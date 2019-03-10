package com.pablojc.marvelapp.ui.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.pablojc.marvelapp.R
import com.pablojc.marvelapp.domain.models.Hero
import com.pablojc.marvelapp.ui.features.herodetail.fragments.HeroGroupsFragment
import com.pablojc.marvelapp.ui.features.herodetail.fragments.HeroLifeFragment
import com.pablojc.marvelapp.ui.features.herodetail.fragments.HeroPowerFragment

class HeroDetailPagerAdapter(fm: FragmentManager, private val context: Context, val hero: Hero) : FragmentPagerAdapter(fm) {

    private val fragmentList = arrayListOf<Fragment>()

    init {
        setTabs()
    }

    private fun setTabs() {
        hero.apply{
            fragmentList.add(HeroLifeFragment.newInstance(realName,height))
            fragmentList.add(HeroPowerFragment.newInstance(power,abilities))
            fragmentList.add(HeroGroupsFragment.newInstance(groups))
        }
    }

    override fun getItem(position: Int)= fragmentList[position]

    override fun getCount()= fragmentList.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> context.getString(R.string.life)
            1 -> context.getString(R.string.power)
            else -> context.getString(R.string.groups)
        }
    }

}