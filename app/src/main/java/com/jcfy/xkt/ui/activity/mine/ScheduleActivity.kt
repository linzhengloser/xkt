package com.jcfy.xkt.ui.activity.mine

import android.os.Bundle
import android.support.v7.widget.AppCompatTextView
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseActivity
import com.jcfy.xkt.setTitleText
import com.jcfy.xkt.ui.fragment.ScheduleFragment
import com.jcfy.xkt.ui.itemdecoration.MyViewPagerAdapter
import com.jcfy.xkt.utils.selection.SelectionAdapter
import com.jcfy.xkt.utils.selection.TabTextViewSectionBinder
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.layout_tab.*

/**
 * @author linzheng
 */
class ScheduleActivity : BaseActivity() {

    private val mSelectionAdapter = SelectionAdapter().apply {
        register(AppCompatTextView::class.java, TabTextViewSectionBinder())
        singleSelection()
        setListener { index, _ ->
            vp_schedule.currentItem = index
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        setTitleText("进度")
        vp_schedule.adapter = MyViewPagerAdapter(supportFragmentManager, arrayListOf(
                ScheduleFragment.newInstance(),
                ScheduleFragment.newInstance()
        ))
        mSelectionAdapter.bindViewPager(vp_schedule, ll_tab)
    }

}