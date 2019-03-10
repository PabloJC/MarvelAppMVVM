package com.pablojc.marvelapp.ui.features.herodetail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pablojc.marvelapp.R
import kotlinx.android.synthetic.main.fragment_groups.*

class HeroGroupsFragment : Fragment() {

    companion object {

        const val GROUP_LIST = "group_list"

        fun newInstance(groups: String?): HeroGroupsFragment {
            val fragment = HeroGroupsFragment()
            val args = Bundle()
            args.putString(GROUP_LIST,groups)
            fragment.arguments = args
            return fragment
        }
    }

    private val groups: String?
        get() = arguments?.getString(GROUP_LIST)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_groups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lbGroups.setContent(groups)
    }

}