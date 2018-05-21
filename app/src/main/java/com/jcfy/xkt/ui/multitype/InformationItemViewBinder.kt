package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.Information
import com.jcfy.xkt.ui.activity.InformationDetailActivity
import com.jcfy.xkt.utils.GlideApp
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_information.view.*
import me.drakeet.multitype.ItemViewBinder
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

/**
 * @author linzheng
 */
class InformationItemViewBinder : ItemViewBinder<Information, InformationItemViewBinder.InformationViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = InformationViewHolder(inflater.inflate(R.layout.item_information, parent, false))

    override fun onBindViewHolder(holder: InformationViewHolder, item: Information) {
        holder.itemView.apply {
            tv_title.text = item.title
            tv_content.text = item.subhead
            GlideApp.with(context)
                    .load(item.imgUrl)
                    .into(iv_cover)
            holder.itemView.onClick {
                it?.context?.startActivity<InformationDetailActivity>("informationId" to item.newsId)
            }
        }
    }

    class InformationViewHolder(itemView: View) : BaseViewHolder(itemView)

}