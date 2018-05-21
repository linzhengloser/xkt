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
class MainInformationItemViewBinder : ItemViewBinder<Information, MainInformationItemViewBinder.MainInformationViewHolder>() {
    override fun onBindViewHolder(holder: MainInformationViewHolder, item: Information) {
        holder.itemView.apply {
            tv_title.text = item.title
            GlideApp.with(context)
                    .load(item.imgUrl)
                    .into(iv_cover)
            onClick {
                it?.context?.startActivity<InformationDetailActivity>("informationId" to item.newsId)
            }
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = MainInformationViewHolder(inflater.inflate(R.layout.item_main_information, parent, false))

    class MainInformationViewHolder(itemView: View) : BaseViewHolder(itemView)

}
