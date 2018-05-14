package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.lz.baselibrary.base.BaseViewHolder
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class RechargeSubmitItemViewBinder : ItemViewBinder<String, RechargeSubmitItemViewBinder.RechargeSubmitViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
     =RechargeSubmitViewHolder(inflater.inflate(R.layout.item_recharge_submit,parent,false))

    override fun onBindViewHolder(holder: RechargeSubmitViewHolder, item: String) {
    }

    class RechargeSubmitViewHolder(itemView: View) : BaseViewHolder(itemView)

}
