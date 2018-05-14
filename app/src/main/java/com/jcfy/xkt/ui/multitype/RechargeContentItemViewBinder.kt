package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.RechargeContent
import com.lz.baselibrary.base.BaseViewHolder
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class RechargeContentItemViewBinder : ItemViewBinder<RechargeContent, RechargeContentItemViewBinder.RechargeContentViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    = RechargeContentViewHolder(inflater.inflate(R.layout.item_recharge_content,parent,false))

    override fun onBindViewHolder(holder: RechargeContentViewHolder, item: RechargeContent) {
    }


    class RechargeContentViewHolder(itemView : View) : BaseViewHolder(itemView)

}
