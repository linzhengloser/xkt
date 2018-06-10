package com.jcfy.xkt.ui.multitype.mine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.mine.Schedule
import com.jcfy.xkt.parseSchedule
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.multitype.ParentItemViewBinder
import com.lz.baselibrary.utils.OnParentClickListener
import kotlinx.android.synthetic.main.item_question_parent.view.*

/**
 * 错题 or 收藏
 * @author linzheng
 */
class QuestionParentItemViewBinder(onParentClickListener: OnParentClickListener<Schedule>) : ParentItemViewBinder<Schedule, QuestionParentItemViewBinder.QuestionParentViewHolder>(onParentClickListener) {
    override fun onBindViewHolder(holder: QuestionParentViewHolder, item: Schedule) {
        holder.itemView.apply {
            tv_title.text = item.writingsName
            tv_schedule.text = item.schedule.parseSchedule()
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = QuestionParentViewHolder(inflater.inflate(R.layout.item_question_parent, parent, false))

    class QuestionParentViewHolder(itemView: View) : BaseViewHolder(itemView)

}