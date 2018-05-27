package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.Writings
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.multitype.ParentItemViewBinder
import com.lz.baselibrary.utils.OnParentClickListener
import kotlinx.android.synthetic.main.item_chapter_parent.view.*

/**
 * @author linzheng
 */
class ChapterParentItemViewBinder(onParentClickListener: OnParentClickListener<Writings>) : ParentItemViewBinder<Writings, ChapterParentItemViewBinder.ChapterParentViewHolder>(onParentClickListener) {
    override fun onBindViewHolder(holder: ChapterParentViewHolder, item: Writings) {
        holder.itemView.apply {
            tv_title.text  = item.writingsName
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
     = ChapterParentViewHolder(inflater.inflate(R.layout.item_chapter_parent,parent,false))

    class ChapterParentViewHolder(itemView: View) : BaseViewHolder(itemView)

}