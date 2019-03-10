package com.pablojc.marvelapp.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.pablojc.marvelapp.HeroesApp

abstract class BaseActivity: AppCompatActivity() {

    val app: HeroesApp
        get() = application as HeroesApp

    fun setToolbarWithBack(toolbar: Toolbar){
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}