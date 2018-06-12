package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.mine.Recharge
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_recharge_content.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class RechargeContentItemViewBinder : ItemViewBinder<Recharge, RechargeContentItemViewBinder.RechargeContentViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = RechargeContentViewHolder(inflater.inflate(R.layout.item_recharge_content, parent, false))

    override fun onBindViewHolder(holder: RechargeContentViewHolder, item: Recharge) {
        holder.itemView.apply {
            tv_day_count.text = "${item.duration}天"
            tv_price.text = "优惠价${item.preferentialPrice}元"
        }
    }


    class RechargeContentViewHolder(itemView: View) : BaseViewHolder(itemView)

}
