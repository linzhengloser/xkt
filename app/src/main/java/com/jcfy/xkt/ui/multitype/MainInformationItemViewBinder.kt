package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.Information
import com.jcfy.xkt.ui.activity.InformationDetailActivity
import com.lz.baselibrary.base.BaseViewHolder
import me.drakeet.multitype.ItemViewBinder
import org.jetbrains.anko.startActivity

/**
 * @author linzheng
 */
class MainInformationItemViewBinder : ItemViewBinder<Information, MainInformationItemViewBinder.MainInformationViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = MainInformationViewHolder(inflater.inflate(R.layout.item_main_information, parent, false))

    override fun onBindViewHolder(holder: MainInformationViewHolder, item: Information) {
        holder.itemView.setOnClickListener {
            it.context.startActivity<InformationDetailActivity>()
        }
    }


    class MainInformationViewHolder(itemView: View) : BaseViewHolder(itemView)

}
