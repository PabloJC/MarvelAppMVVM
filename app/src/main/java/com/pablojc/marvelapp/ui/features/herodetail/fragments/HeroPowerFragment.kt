package com.pablojc.marvelapp.ui.features.herodetail.fragments

import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pablojc.marvelapp.R
import com.pablojc.marvelapp.ui.base.ScreenState
import com.pablojc.marvelapp.ui.features.herodetail.HeroDetailActivity
import com.pablojc.marvelapp.ui.features.herodetail.HeroDetailState
import kotlinx.android.synthetic.main.fragment_power.*


class HeroPowerFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            (it as HeroDetailActivity).viewModel.heroesState.observe(
                this@HeroPowerFragment, Observer {
                    updateUI(it)
                }
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_power, container, false)
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
                lbPower.setContent(renderState.items.power)
                lbAbilities.setContent(renderState.items.abilities)
            }
        }
    }

    private fun showLoading() {

    }
}