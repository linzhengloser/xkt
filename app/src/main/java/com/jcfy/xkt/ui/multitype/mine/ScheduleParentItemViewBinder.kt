package com.jcfy.xkt.ui.multitype.mine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.mine.Schedule
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.multitype.ParentItemViewBinder
import com.lz.baselibrary.utils.OnParentClickListener

/**
 * @author linzheng
 */
class ScheduleParentItemViewBinder(onParentClickListener: OnParentClickListener<Schedule>) :
        ParentItemViewBinder<Schedule, ScheduleParentItemViewBinder.ScheduleParentViewHolder>(onParentClickListener) {
    override fun onBindViewHolder(holder: ScheduleParentViewHolder, item: Schedule) {
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = ScheduleParentViewHolder(inflater.inflate(R.layout.item_schedule_parent, parent, false))

    class ScheduleParentViewHolder(itemView: View) : BaseViewHolder(itemView)

}