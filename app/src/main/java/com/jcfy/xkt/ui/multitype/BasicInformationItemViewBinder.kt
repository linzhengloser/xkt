package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.BasicInformation
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_basic_information.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */

class BasicInformationItemViewBinder : ItemViewBinder<BasicInformation, BasicInformationItemViewBinder.BasicInformationViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): BasicInformationViewHolder
            = BasicInformationViewHolder(inflater.inflate(R.layout.item_basic_information, parent, false))

    override fun onBindViewHolder(holder: BasicInformationViewHolder, item: BasicInformation) {
        holder.itemView.tv_title.text = item.title
        holder.itemView.tv_content.text = item.content
    }


    class BasicInformationViewHolder(itemView: View) : BaseViewHolder(itemView)

}
