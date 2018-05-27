package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.ExerciseExamination
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_exercise.view.*
import me.drakeet.multitype.ItemViewBinder
import org.greenrobot.eventbus.EventBus

/**
 * @author linzheng
 */
class ExerciseItemViewBinder : ItemViewBinder<ExerciseExamination, ExerciseItemViewBinder.ExerciseViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ExerciseViewHolder = ExerciseViewHolder(inflater.inflate(R.layout.item_exercise, parent, false))

    override fun onBindViewHolder(holder: ExerciseViewHolder, item: ExerciseExamination) {
        holder.itemView.apply {
            iv_icon.setImageResource(item.icon)
            tv_title.text = item.title
            setOnClickListener {
                EventBus.getDefault().post(item)
            }
        }
    }

    class ExerciseViewHolder(itemView: View) : BaseViewHolder(itemView)

}
