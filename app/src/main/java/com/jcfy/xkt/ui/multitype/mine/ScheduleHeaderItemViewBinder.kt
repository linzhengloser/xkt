package com.jcfy.xkt.ui.multitype.mine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.mine.ScheduleWrapper
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_schedule_header.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class ScheduleHeaderItemViewBinder : ItemViewBinder<ScheduleWrapper, ScheduleHeaderItemViewBinder.ScheduleHeaderViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
     = ScheduleHeaderViewHolder(inflater.inflate(R.layout.item_schedule_header,parent,false))

    override fun onBindViewHolder(holder: ScheduleHeaderViewHolder, item: ScheduleWrapper) {
        holder.itemView.apply {
            tv_schedule.text = item.baseSchedule
        }
    }

    class ScheduleHeaderViewHolder(itemView : View) : BaseViewHolder(itemView)

}