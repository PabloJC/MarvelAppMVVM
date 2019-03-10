package com.pablojc.marvelapp.ui.features.herodetail

import android.os.Bundle
import com.pablojc.marvelapp.R
import com.pablojc.marvelapp.di.herodetail.DaggerHeroDetailComponent
import com.pablojc.marvelapp.di.herodetail.HeroDetailComponent
import com.pablojc.marvelapp.domain.interactors.GetHeroDetailInteractor
import com.pablojc.marvelapp.domain.models.Hero
import com.pablojc.marvelapp.ui.adapters.HeroDetailPagerAdapter
import com.pablojc.marvelapp.ui.base.BaseActivity
import com.pablojc.marvelapp.ui.extensions.*
import kotlinx.android.synthetic.main.activity_hero_detail.*
import javax.inject.Inject

class HeroDetailActivity : BaseActivity() {

    private lateinit var viewModel: HeroDetailViewModel
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

        setToolbarWithBack(toolbar)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = withViewModel({ HeroDetailViewModel(heroId,heroDetailInteractor) }){
            observe(heroDetailState,::renderState)
        }
    }

    private fun renderState(state: HeroDetailState){
        return when(state){
            is HeroDetailState.ShowHero -> renderHero(state.hero)
            HeroDetailState.ShowError -> showToast(R.string.error_generic)
        }
    }

    private fun renderHero(hero: Hero) {
        hero.apply {
            tvName.text = name
            ivHeroAvatar.showCircle(photo)
            viewPagerAdapter = HeroDetailPagerAdapter(supportFragmentManager, this@HeroDetailActivity,this)
            initViewPagerWithTabs(viewPager,tabLayout,viewPagerAdapter)
        }

    }

    companion object {
        const val HERO_ID = "hero_id"
    }
}
