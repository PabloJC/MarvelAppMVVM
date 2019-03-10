package com.pablojc.marvelapp.ui.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair

fun <T> Activity.startActivityWithTransition(toActivity: Class<T>,extras: Bundle, pair: Pair<View,String>){
    val intent = Intent(this, toActivity)
    intent.putExtras(extras)
    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair)
    startActivity(intent, options.toBundle())
}