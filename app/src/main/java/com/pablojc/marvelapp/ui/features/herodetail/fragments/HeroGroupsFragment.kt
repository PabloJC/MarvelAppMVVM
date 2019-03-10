package com.pablojc.marvelapp.ui.features.herodetail.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pablojc.marvelapp.R
import com.pablojc.marvelapp.application.HeroesApp
import com.pablojc.marvelapp.ui.base.ScreenState
import com.pablojc.marvelapp.ui.features.herodetail.HeroDetailActivity
import com.pablojc.marvelapp.ui.features.herodetail.HeroDetailState
import kotlinx.android.synthetic.main.fragment_groups.*

class HeroGroupsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            (it as HeroDetailActivity).viewModel.heroesState.observe(
                this@HeroGroupsFragment, Observer {
                    updateUI(it)
                }
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_groups, container, false)
    }

    private fun updateUI(screenState: ScreenState<HeroDetailState>) {
        return when(screenState){
            is ScreenState.Loading -> showLoading()
            is ScreenState.ShowSuccess -> renderData(screenState.renderState)
            is ScreenState.ShowError -> showError()
        }
    }

    private fun showError() {

    }

    private fun renderData(renderState: HeroDetailState) {
        return when(renderState){
            is HeroDetailState.ShowHero -> {
                lbGroups.setContent(TextUtils.join("\n",renderState.items.groups))
            }
        }
    }

    private fun showLoading() {

    }

}