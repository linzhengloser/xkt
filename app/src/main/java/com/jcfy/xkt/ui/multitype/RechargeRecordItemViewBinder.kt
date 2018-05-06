package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.RechargeRecord
import com.lz.baselibrary.base.BaseViewHolder
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class RechargeRecordItemViewBinder : ItemViewBinder<RechargeRecord, RechargeRecordItemViewBinder.RechargeRecordViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    = RechargeRecordViewHolder(inflater.inflate(R.layout.item_recharge_record,parent,false))

    override fun onBindViewHolder(holder: RechargeRecordViewHolder, item: RechargeRecord) {
    }


    class RechargeRecordViewHolder(itemView:View) : BaseViewHolder(itemView)

}
