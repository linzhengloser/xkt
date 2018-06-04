package com.jcfy.xkt.ui.multitype.mine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.mine.ScheduleWrapper
import com.lz.baselibrary.base.BaseViewHolder
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class ScheduleHeaderItemViewBinder : ItemViewBinder<ScheduleWrapper, ScheduleHeaderItemViewBinder.ScheduleHeaderViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
     = ScheduleHeaderViewHolder(inflater.inflate(R.layout.item_schedule_header,parent,false))

    override fun onBindViewHolder(holder: ScheduleHeaderViewHolder, item: ScheduleWrapper) {

    }

    class ScheduleHeaderViewHolder(itemView : View) : BaseViewHolder(itemView)

}