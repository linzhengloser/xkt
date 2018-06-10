package com.jcfy.xkt.ui.fragment

import android.view.View
import com.jcfy.xkt.module.mine.Schedule
import com.jcfy.xkt.module.mine.ScheduleWrapper
import com.lz.baselibrary.utils.OnParentClickListener
import com.lz.baselibrary.view.RefreshListener
import kotlinx.android.synthetic.main.fragment_schedule.*
import org.greenrobot.eventbus.Subscribe

/**
 * @author linzheng
 */


open class PrimaryOrIntermediateQuestionScheduleFragment : PrimaryOrIntermediateFragment(), OnParentClickListener<Schedule>, RefreshListener {

    @Subscribe
    public open fun onScheduleEvent(event: ScheduleWrapper) {
        if (event.writingBaseList.isEmpty() || event.writingMiddleList.isEmpty()) {
            showEmptyDataLayout()
            return
        }
        if (mType == TYPE_PRIMARY) {
            initData(event.writingBaseList)
        } else {
            initData(event.writingMiddleList)
        }
        showSuccessLayout()
        srl_schedule.refreshComplete()
        mAdapter.notifyDataSetChanged()
    }

    override fun onParentClick(v: View, parent: Schedule) {

    }

    private fun initData(scheduleList: List<Schedule>) {
        scheduleList.forEach {
            mItems.add(it)
            it.chapterList.forEach {
                mItems.add(it)
            }
        }
    }
}