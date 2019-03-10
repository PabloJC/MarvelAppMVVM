package com.pablojc.marvelapp.ui.features.herolist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.core.util.Pair
import com.google.android.material.snackbar.Snackbar
import com.pablojc.marvelapp.R
import com.pablojc.marvelapp.domain.interactors.GetHeroListInteractor
import com.pablojc.marvelapp.domain.models.Failure
import com.pablojc.marvelapp.ui.adapters.HeroListAdapter
import com.pablojc.marvelapp.ui.base.BaseActivity
import com.pablojc.marvelapp.ui.base.ScreenState
import com.pablojc.marvelapp.ui.features.herodetail.HeroDetailActivity
import com.pablojc.marvelapp.ui.features.herolist.di.DaggerHeroListComponent
import com.pablojc.marvelapp.ui.features.herolist.di.HeroListComponent
import kotlinx.android.synthetic.main.activity_hero_list.*
import javax.inject.Inject
import com.pablojc.marvelapp.ui.utils.observe
import com.pablojc.marvelapp.ui.utils.withViewModel

class HeroListActivity : BaseActivity() {

    private lateinit var viewModel: HeroListViewModel
    private lateinit var mHeroesAdapter: HeroListAdapter

    @Inject
    lateinit var heroListInteractor: GetHeroListInteractor

    val component: HeroListComponent by lazy {
        DaggerHeroListComponent.builder()
            .appComponent(app.component)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_list)
        component.inject(this)

        initViews()
        initViewModel()
    }

    private fun updateUI(screenState: ScreenState<HeroListState>?) {
        return when(screenState){
            is ScreenState.Loading -> showLoading()
            is ScreenState.ShowSuccess-> renderData(screenState.renderState)
            is ScreenState.ShowError -> showError(screenState.error)
            else -> showError()
        }
    }

    private fun initViews() {
        val layoutManager = LinearLayoutManager(this)
        rvHeroList.layoutManager = layoutManager
        mHeroesAdapter = HeroListAdapter { item, view -> goToDetail(item, view) }
        rvHeroList.adapter = mHeroesAdapter

        swipeRefresh.setOnRefreshListener { viewModel.reloadList() }    }

    private fun initViewModel() {
        viewModel = withViewModel({ HeroListViewModel(heroListInteractor) }){
            observe(heroesState,::updateUI)
        }
    }

    private fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    private fun renderData(renderState: HeroListState){
        swipeRefresh.isRefreshing = false
        return when(renderState){
            is HeroListState.ShowHeroList -> mHeroesAdapter.submitData(renderState.items)
        }
    }
    private fun showError(error: Failure? = null) {
        val snackbarError = Snackbar.make(coordinator, R.string.error_generic, Snackbar.LENGTH_SHORT)
        snackbarError.setAction(R.string.retry){
            viewModel.reloadList()
        }
        snackbarError.show()
    }

    private fun goToDetail(heroId: Long?, view: View) {
        heroId?.apply {

            val detailIntent = Intent(this@HeroListActivity, HeroDetailActivity::class.java)
            detailIntent.putExtra(HeroDetailActivity.HERO_ID, this)

            val imageViewPair = Pair.create(view, "heroAvatar")
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@HeroListActivity, imageViewPair)

            startActivity(detailIntent, options.toBundle())


        }
    }
}
