package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.Exercise
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_exercise.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class ExerciseItemViewBinder : ItemViewBinder<Exercise, ExerciseItemViewBinder.ExerciseViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ExerciseViewHolder
        = ExerciseViewHolder(inflater.inflate(R.layout.item_exercise,parent,false))

    override fun onBindViewHolder(holder: ExerciseViewHolder, item: Exercise) {
        holder.itemView.iv_icon.setImageResource(item.icon)
        holder.itemView.tv_title.text = item.title
    }

    class ExerciseViewHolder(itemView: View) : BaseViewHolder(itemView)

}
