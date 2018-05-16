package com.jcfy.xkt.ui.itemdecoration

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @author linzheng
 */
class MyViewPagerAdapter(fragmentManager: FragmentManager, private val fragmentList: List<Fragment>) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size
}
