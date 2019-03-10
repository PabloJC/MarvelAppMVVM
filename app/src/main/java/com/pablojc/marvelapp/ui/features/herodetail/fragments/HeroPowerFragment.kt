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

    companion object {

        const val POWER = "power"
        const val ABILITIES = "ABILITIES"

        fun newInstance(power: String?, abilities: String?): HeroPowerFragment {
            val fragment = HeroPowerFragment()
            val args = Bundle()
            args.putString(POWER, power)
            args.putString(ABILITIES, abilities)
            fragment.arguments = args
            return fragment
        }
    }

    private val power: String?
        get() = arguments?.getString(POWER)

    private val abilities: String?
        get() = arguments?.getString(ABILITIES)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_power, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lbPower.setContent(power)
        lbAbilities.setContent(abilities)
    }
}