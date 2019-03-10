package com.pablojc.marvelapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pablojc.marvelapp.R
import com.pablojc.marvelapp.domain.models.HeroItemList
import com.pablojc.marvelapp.ui.adapters.holders.HeroListViewHolder
import com.pablojc.marvelapp.ui.features.herolist.HeroListActivity

class HeroListAdapter(val activity: HeroListActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mHeroList = mutableListOf<HeroItemList>()


    fun submitData(list: List<HeroItemList>) {
        this.mHeroList.clear()
        this.mHeroList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hero_list, parent, false)

        return HeroListViewHolder(v,activity)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HeroListViewHolder).bindItem(mHeroList[position])
    }

    override fun getItemCount(): Int = mHeroList.size
}