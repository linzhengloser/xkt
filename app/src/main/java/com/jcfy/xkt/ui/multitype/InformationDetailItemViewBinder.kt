package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.InformationDetail
import com.lz.baselibrary.base.BaseViewHolder
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class InformationDetailItemViewBinder : ItemViewBinder<InformationDetail, InformationDetailItemViewBinder.InformationDetailViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
     = InformationDetailViewHolder(inflater.inflate(R.layout.item_information_detail,parent,false))

    override fun onBindViewHolder(holder: InformationDetailViewHolder, item: InformationDetail) {
    }

    class InformationDetailViewHolder(itemView: View) : BaseViewHolder(itemView)

}
