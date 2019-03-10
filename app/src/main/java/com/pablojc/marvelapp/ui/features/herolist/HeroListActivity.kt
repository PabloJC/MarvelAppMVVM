package com.pablojc.marvelapp.ui.features.herolist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pablojc.marvelapp.R
import com.pablojc.marvelapp.di.herolist.DaggerHeroListComponent
import com.pablojc.marvelapp.di.herolist.HeroListComponent
import com.pablojc.marvelapp.di.herolist.HeroListModule
import com.pablojc.marvelapp.domain.interactors.GetHeroListInteractor
import com.pablojc.marvelapp.ui.adapters.HeroListAdapter
import com.pablojc.marvelapp.ui.base.BaseActivity
import com.pablojc.marvelapp.ui.base.ScreenState
import com.pablojc.marvelapp.ui.extensions.observe
import com.pablojc.marvelapp.ui.extensions.showToast
import com.pablojc.marvelapp.ui.extensions.withViewModel
import kotlinx.android.synthetic.main.activity_hero_list.*
import javax.inject.Inject

class HeroListActivity : BaseActivity() {

    private lateinit var viewModel: HeroListViewModel

    @Inject
    lateinit var mHeroesAdapter: HeroListAdapter

    @Inject
    lateinit var heroListInteractor: GetHeroListInteractor

    val component: HeroListComponent by lazy {
        DaggerHeroListComponent.builder()
            .appComponent(app.component)
            .heroListModule(HeroListModule(this))
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_list)
        component.inject(this)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        val layoutManager = LinearLayoutManager(this)
        rvHeroList.layoutManager = layoutManager
        rvHeroList.adapter = mHeroesAdapter

        swipeRefresh.setOnRefreshListener { viewModel.reloadList() }    }

    private fun initViewModel() {
        viewModel = withViewModel({ HeroListViewModel(heroListInteractor) }){
            observe(heroListState,::updateUI)
        }
    }

    private fun updateUI(screenState: ScreenState<HeroListState>) {
        return when(screenState){
            is ScreenState.Loading -> showLoading()
            is ScreenState.Render-> renderData(screenState.renderState)
        }
    }

    private fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    private fun renderData(renderState: HeroListState){
        swipeRefresh.isRefreshing = false
        return when(renderState){
            is HeroListState.ShowHeroList -> mHeroesAdapter.submitData(renderState.items)
            is HeroListState.ShowError -> showToast(renderState.messageRes)
        }
    }
}
