package com.pablojc.marvelapp.ui.features.herodetail

import android.os.Bundle
import com.pablojc.marvelapp.R
import com.pablojc.marvelapp.domain.interactors.GetHeroDetailInteractor
import com.pablojc.marvelapp.domain.models.Failure
import com.pablojc.marvelapp.domain.models.Hero
import com.pablojc.marvelapp.ui.adapters.HeroDetailPagerAdapter
import com.pablojc.marvelapp.ui.base.BaseActivity
import com.pablojc.marvelapp.ui.base.ScreenState
import com.pablojc.marvelapp.ui.features.herodetail.di.DaggerHeroDetailComponent
import com.pablojc.marvelapp.ui.features.herodetail.di.HeroDetailComponent
import com.pablojc.marvelapp.ui.utils.observe
import com.pablojc.marvelapp.ui.utils.showCircle
import com.pablojc.marvelapp.ui.utils.withViewModel
import kotlinx.android.synthetic.main.activity_hero_detail.*
import javax.inject.Inject

class HeroDetailActivity : BaseActivity() {

    lateinit var viewModel: HeroDetailViewModel
    private lateinit var viewPagerAdapter: HeroDetailPagerAdapter

    @Inject
    lateinit var heroDetailInteractor: GetHeroDetailInteractor

    val component: HeroDetailComponent by lazy {
        DaggerHeroDetailComponent.builder()
            .appComponent(app.component)
            .build()
    }


    private val heroId: Long?
        get() = intent.getLongExtra(HERO_ID,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)
        component.inject(this)


        initViews()
        initViewModel()
    }

    private fun updateUI(screenState: ScreenState<HeroDetailState>?) {
        return when(screenState){
            is ScreenState.Loading -> showLoading()
            is ScreenState.ShowSuccess-> renderData(screenState.renderState)
            is ScreenState.ShowError -> showError(screenState.error)
            else -> showError()
        }
    }

    private fun initViews() {

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressed() }

        viewPagerAdapter = HeroDetailPagerAdapter(supportFragmentManager, this)

        viewPager.adapter = viewPagerAdapter
        viewPager.offscreenPageLimit = 3
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun initViewModel() {
        viewModel = withViewModel({ HeroDetailViewModel(heroId,heroDetailInteractor) }){
            observe(heroesState,::updateUI)
        }
    }

    private fun showLoading() {
    }

    private fun renderData(renderState: HeroDetailState) {
        return when(renderState){
            is HeroDetailState.ShowHero -> renderHero(renderState.items)
        }
    }

    private fun showError(error: Failure? = null) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun renderHero(items: Hero) {
        tvName.text = items.name
        ivHeroAvatar.showCircle(items.photo)
    }

    companion object {
        const val HERO_ID = "hero_id"
    }
}
