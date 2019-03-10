package com.pablojc.marvelapp.ui.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.showCircle(uri: String?, placeholderRes: Int? = null){
    val builder = Glide.with(context)
        .load(uri)
        .apply(RequestOptions.circleCropTransform())

    placeholderRes?.apply {
        builder.placeholder(placeholderRes)
    }

    builder.into(this)
}

fun ImageView.showDrawable(drawable: Drawable?, placeholderRes: Int? = null){
    val builder = Glide.with(context)
        .load(drawable)

    placeholderRes?.apply {
        builder.placeholder(placeholderRes)
    }

    builder.into(this)
}

