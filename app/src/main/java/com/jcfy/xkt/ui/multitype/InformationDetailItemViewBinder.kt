package com.jcfy.xkt.ui.multitype

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.InformationDetail
import com.jcfy.xkt.utils.GlideApp
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_information_detail.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class InformationDetailItemViewBinder : ItemViewBinder<InformationDetail, InformationDetailItemViewBinder.InformationDetailViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = InformationDetailViewHolder(inflater.inflate(R.layout.item_information_detail, parent, false))

    override fun onBindViewHolder(holder: InformationDetailViewHolder, item: InformationDetail) {
        holder.itemView.run {
            GlideApp.with(context).load(item.downloadImgUrl).into(iv_cover)
            tv_title.text = item.title
            tv_date.text = item.date
            tv_author.text = item.author
            tv_origin.text = item.origin
            tv_content.text = Html.fromHtml(item.content)
        }
    }

    class InformationDetailViewHolder(itemView: View) : BaseViewHolder(itemView)

}
