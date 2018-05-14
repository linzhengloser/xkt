package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.bindBoolean2Visibility
import com.jcfy.xkt.module.RechargeTypeName
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_recharge_type_name.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class RechargeTypeNameItemViewBinder : ItemViewBinder<RechargeTypeName, RechargeTypeNameItemViewBinder.RechargeTypeNameViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = RechargeTypeNameViewHolder(inflater.inflate(R.layout.item_recharge_type_name, parent, false))

    override fun onBindViewHolder(holder: RechargeTypeNameViewHolder, item: RechargeTypeName) {
        holder.itemView.tv_title.text = item.title
        holder.itemView.v_divider.bindBoolean2Visibility(item.isShowDivider)
    }

    class RechargeTypeNameViewHolder(itemView: View) : BaseViewHolder(itemView)

}
