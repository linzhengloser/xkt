package com.jcfy.xkt.ui.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.jcfy.xkt.module.Banner
import com.jcfy.xkt.module.BannerWrapper
import com.jcfy.xkt.utils.GlideApp

/**
 * @author linzheng
 */
abstract class BannerAdapter(private val bannerWrapper: BannerWrapper) : PagerAdapter() {


    override fun getCount(): Int {
        return 30000
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val newPosition = position % bannerWrapper.bannerList.size
        val imageView = ImageView(container.context).apply {
            scaleType = ImageView.ScaleType.FIT_XY
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setOnClickListener { v -> onItemClickListener(bannerWrapper.bannerList[newPosition], v) }
            GlideApp.with(context).load(bannerWrapper.bannerList[newPosition].imgUrl).into(this)
        }
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
        container.removeView(any as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    abstract fun onItemClickListener(data: Banner, view: View)

}
