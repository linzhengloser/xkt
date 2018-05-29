package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.bindBoolean2Visibility
import com.jcfy.xkt.module.Chapter
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_chapter_child.view.*
import me.drakeet.multitype.ItemViewBinder
import org.greenrobot.eventbus.EventBus

/**
 * @author linzheng
 */
class ChapterChildItemViewBinder : ItemViewBinder<Chapter, ChapterChildItemViewBinder.ChapterChildViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = ChapterChildViewHolder(inflater.inflate(R.layout.item_chapter_child, parent, false))

    override fun onBindViewHolder(holder: ChapterChildViewHolder, item: Chapter) {
        holder.itemView.apply {
            tv_title.text = item.chapterName
            tv_free.bindBoolean2Visibility(item.isFree == 0)
            setOnClickListener {
                EventBus.getDefault().post(item)
            }
        }
    }


    class ChapterChildViewHolder(itemView: View) : BaseViewHolder(itemView)

}