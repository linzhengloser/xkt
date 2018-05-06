package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.Mine
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_mine.view.*
import me.drakeet.multitype.ItemViewBinder
import org.greenrobot.eventbus.EventBus

/**
 * @author linzheng
 */
class MineItemViewBinder : ItemViewBinder<Mine, MineItemViewBinder.MineViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): MineViewHolder = MineViewHolder(inflater.inflate(R.layout.item_mine, parent, false))

    override fun onBindViewHolder(holder: MineViewHolder, item: Mine) {
        holder.itemView.iv_icon.setImageResource(item.icon)
        holder.itemView.tv_title.text = item.title
        holder.itemView.setOnClickListener {
            EventBus.getDefault().post(item)
        }
    }

    class MineViewHolder(itemView: View) : BaseViewHolder(itemView)
}
