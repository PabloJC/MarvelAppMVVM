package com.pablojc.marvelapp.ui.adapters.holders

import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pablojc.marvelapp.domain.models.HeroItemList
import com.pablojc.marvelapp.ui.utils.showCircle
import kotlinx.android.synthetic.main.item_hero_list.view.*

class HeroListViewHolder(view: View, callback: (Long,View) -> Unit) : RecyclerView.ViewHolder(view) {

    private var item: HeroItemList? = null


    init {
        itemView.setOnClickListener {
            item?.let {
                callback(it.id,itemView.ivHeroAvatar)
            }
        }
    }

    fun bindItem(contactListItem: HeroItemList?) {
        this.item = contactListItem
        item?.let {
            itemView.tvHeroName.text = it.name
            itemView.ivHeroAvatar.showCircle(it.photo)
            itemView.tvGroups.text = TextUtils.join(",",it.groups)
        }
    }

}