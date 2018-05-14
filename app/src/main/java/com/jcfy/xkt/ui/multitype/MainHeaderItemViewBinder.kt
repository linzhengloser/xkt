package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.ui.activity.InformationActivity
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_main_header.view.*
import me.drakeet.multitype.ItemViewBinder
import org.jetbrains.anko.startActivity

/**
 * @author linzheng
 */
class MainHeaderItemViewBinder : ItemViewBinder<String, MainHeaderItemViewBinder.MainHeaderViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
     = MainHeaderViewHolder(inflater.inflate(R.layout.item_main_header,parent,false))

    override fun onBindViewHolder(holder: MainHeaderViewHolder, item: String) {
        holder.itemView.tv_more.setOnClickListener {
            holder.itemView.context.startActivity<InformationActivity>()
        }
    }

    class MainHeaderViewHolder(itemView: View) : BaseViewHolder(itemView)

}
