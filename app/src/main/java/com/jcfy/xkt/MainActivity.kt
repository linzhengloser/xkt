package com.jcfy.xkt

import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import com.jcfy.xkt.base.BaseActivity
import com.jcfy.xkt.ui.fragment.ExerciseFragment
import com.jcfy.xkt.ui.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val mFragmentList = listOf(
            ExerciseFragment.newInstance(),
            MineFragment.newInstance()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goneBack()
        setTitleText("首页")
        vp_main.offscreenPageLimit = mFragmentList.size
        vp_main.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = mFragmentList[position]

            override fun getCount() = mFragmentList.size
        }


    }


}
