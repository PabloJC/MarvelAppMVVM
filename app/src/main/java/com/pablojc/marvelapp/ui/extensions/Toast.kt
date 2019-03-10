package com.pablojc.marvelapp.ui.extensions

import android.content.Context
import android.widget.Toast

fun Context.showToast(resId: Int){
    Toast.makeText(this,resId,Toast.LENGTH_SHORT).show()
}