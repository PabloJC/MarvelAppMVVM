package com.pablojc.marvelapp.ui.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.pablojc.marvelapp.R
import com.pablojc.marvelapp.ui.extensions.showDrawable
import kotlinx.android.synthetic.main.view_life_box.view.*

class LifeBoxView : LinearLayout{

    private var title: String? = null
    private var icon: Drawable? = null

    constructor(context: Context) : super(context) {
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {

        val ta = context.obtainStyledAttributes(attrs, R.styleable.LifeBoxView, 0, 0)
        try {
            title = ta.getString(R.styleable.LifeBoxView_box_title)
            icon = ta.getDrawable(R.styleable.LifeBoxView_box_icon)
        } finally {
            ta.recycle()
        }

        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_life_box, this, true)

        if(!title.isNullOrEmpty()){
            tvTitle.visibility = View.VISIBLE
            tvTitle.text = title
        }

        icon?.apply {
            ivIcon.visibility = View.VISIBLE
            ivIcon.showDrawable(this)
        }
    }

    fun setContent(text: String?){
        tvContent.text = text
    }
}