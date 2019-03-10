package com.pablojc.marvelapp.ui.adapters.holders

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.pablojc.marvelapp.domain.models.HeroItemList
import com.pablojc.marvelapp.ui.extensions.showCircle
import com.pablojc.marvelapp.ui.extensions.startActivityWithTransition
import com.pablojc.marvelapp.ui.features.herodetail.HeroDetailActivity
import kotlinx.android.synthetic.main.item_hero_list.view.*

class HeroListViewHolder(view: View,activity: Activity) : RecyclerView.ViewHolder(view) {

    private var item: HeroItemList? = null


    init {
        itemView.setOnClickListener {
            item?.let {
                val bundle = Bundle()
                bundle.putLong(HeroDetailActivity.HERO_ID, it.id)
                activity.startActivityWithTransition(
                    HeroDetailActivity::class.java,bundle,
                    Pair.create(itemView.ivHeroAvatar as View, "heroAvatar"))
            }
        }
    }

    fun bindItem(contactListItem: HeroItemList?) {
        this.item = contactListItem
        item?.apply {
            itemView.tvHeroName.text = name
            itemView.ivHeroAvatar.showCircle(photo)
            itemView.tvGroups.text = groups
        }
    }

}