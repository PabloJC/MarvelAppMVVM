package com.pablojc.marvelapp.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.pablojc.marvelapp.application.HeroesApp

abstract class BaseActivity: AppCompatActivity() {

    val app: HeroesApp
        get() = application as HeroesApp

}