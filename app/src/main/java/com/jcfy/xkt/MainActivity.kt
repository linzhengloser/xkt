package com.jcfy.xkt

import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.jcfy.xkt.base.BaseActivity
import com.jcfy.xkt.ui.fragment.ExaminationFragment
import com.jcfy.xkt.ui.fragment.ExerciseFragment
import com.jcfy.xkt.ui.fragment.MainFragment
import com.jcfy.xkt.ui.fragment.MineFragment
import com.jcfy.xkt.ui.view.NavigationItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), ViewPager.OnPageChangeListener {

    private val mTitleTextList = listOf(
            "首页", "练习", "考试", "我的"
    )

    private val mNavigationItems = listOf(
            NavigationItem.createNavigationItem(R.drawable.main_menu_main_normal, R.drawable.main_menu_main_press, mTitleTextList[0]),
            NavigationItem.createNavigationItem(R.drawable.main_menu_exercise_normal, R.drawable.main_menu_exercise_press, mTitleTextList[1]),
            NavigationItem.createNavigationItem(R.drawable.main_menu_examination_normal, R.drawable.main_menu_examination_press, mTitleTextList[2]),
            NavigationItem.createNavigationItem(R.drawable.main_menu_mine_normal, R.drawable.main_menu_mine_press, mTitleTextList[3])
    )


    private val mFragmentList = listOf(
            MainFragment.newInstance(),
            ExerciseFragment.newInstance(),
            ExaminationFragment.newInstance(),
            MineFragment.newInstance()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goneBack()
        setTitleText(mTitleTextList[0])
        vp_main.offscreenPageLimit = mFragmentList.size
        vp_main.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = mFragmentList[position]

            override fun getCount() = mFragmentList.size
        }
        ll_navigation_layout.setNavigationItems(mNavigationItems, vp_main)
        vp_main.addOnPageChangeListener(this)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        setTitleText(mTitleTextList[position])
    }

}
