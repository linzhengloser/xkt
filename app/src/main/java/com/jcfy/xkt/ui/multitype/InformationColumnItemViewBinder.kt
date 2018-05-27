package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.Column
import com.jcfy.xkt.utils.GlideApp
import com.lz.baselibrary.base.BaseViewHolder
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_information_column.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class InformationColumnItemViewBinder : ItemViewBinder<Column, InformationColumnItemViewBinder.ColumnViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = ColumnViewHolder(inflater.inflate(R.layout.item_information_column, parent, false))

    override fun onBindViewHolder(holder: ColumnViewHolder, item: Column) {
        holder.itemView.apply {
            GlideApp.with(context).load(item.imgUrl).into(iv_cover)
            tv_date.text = item.createDate
            tv_desc.text = item.columnDesc
        }
    }


    class ColumnViewHolder(itemView: View) : BaseViewHolder(itemView)

}
