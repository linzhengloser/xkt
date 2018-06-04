package com.jcfy.xkt.ui.multitype.mine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.Chapter
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.multitype.ChildItemViewBinder

/**
 * @author linzheng
 */
class QuestionChildItemViewBinder : ChildItemViewBinder<Chapter, QuestionChildItemViewBinder.QuestionChildViewHolder>() {
    override fun onBindViewHolder(holder: QuestionChildViewHolder, item: Chapter) {
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
     = QuestionChildViewHolder(inflater.inflate(R.layout.item_question_child,parent,false))

    class QuestionChildViewHolder(itemView : View) : BaseViewHolder(itemView)

}