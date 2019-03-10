package com.pablojc.marvelapp.ui.features.herodetail.fragments

import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pablojc.marvelapp.R
import com.pablojc.marvelapp.domain.models.Hero
import com.pablojc.marvelapp.ui.base.ScreenState
import com.pablojc.marvelapp.ui.features.herodetail.HeroDetailActivity
import com.pablojc.marvelapp.ui.features.herodetail.HeroDetailState
import kotlinx.android.synthetic.main.fragment_life_data.*


class HeroLifeFragment : Fragment() {

    companion object {

        const val REAL_NAME = "real_name"
        const val HEIGHT = "height"

        fun newInstance(fullName: String?, height: String?): HeroLifeFragment {
            val fragment = HeroLifeFragment()
            val args = Bundle()
            args.putString(REAL_NAME,fullName)
            args.putString(HEIGHT,height)
            fragment.arguments = args
            return fragment
        }
    }

    private val realName: String?
        get() = arguments?.getString(REAL_NAME)

    private val height: String?
        get() = arguments?.getString(HEIGHT)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_life_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lbRealName.setContent(realName)
        lbHeight.setContent(height)
    }
}