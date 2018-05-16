package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.RechargeContent
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_recharge_content.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class RechargeContentItemViewBinder : ItemViewBinder<RechargeContent, RechargeContentItemViewBinder.RechargeContentViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = RechargeContentViewHolder(inflater.inflate(R.layout.item_recharge_content, parent, false))

    override fun onBindViewHolder(holder: RechargeContentViewHolder, item: RechargeContent) {
        item.takeIf { it.isType }.apply {
            holder.itemView.tv_day_count.text = item.content
            holder.itemView.tv_price.visibility = View.GONE
        }
    }


    class RechargeContentViewHolder(itemView: View) : BaseViewHolder(itemView)

}
