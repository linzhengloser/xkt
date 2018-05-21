package com.jcfy.xkt.ui.multitype

import android.support.v7.widget.AppCompatImageView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.Banner
import com.jcfy.xkt.module.BannerWrapper
import com.jcfy.xkt.ui.activity.InformationActivity
import com.jcfy.xkt.ui.adapter.BannerAdapter
import com.jcfy.xkt.utils.selection.BannerImageViewSelectionBinder
import com.jcfy.xkt.utils.selection.SelectionAdapter
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_main_header.view.*
import me.drakeet.multitype.ItemViewBinder
import org.jetbrains.anko.startActivity

/**
 * @author linzheng
 */
class MainHeaderItemViewBinder : ItemViewBinder<BannerWrapper, MainHeaderItemViewBinder.MainHeaderViewHolder>() {

    private val mSelectionAdapter: SelectionAdapter = SelectionAdapter().apply {
        register(AppCompatImageView::class.java, BannerImageViewSelectionBinder())
        singleSelection()
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = MainHeaderViewHolder(inflater.inflate(R.layout.item_main_header, parent, false))

    override fun onBindViewHolder(holder: MainHeaderViewHolder, item: BannerWrapper) {
        holder.itemView.apply {
            vp_banner.adapter = object : BannerAdapter(item) {
                override fun onItemClickListener(data: Banner, view: View) {
                }
            }
            mSelectionAdapter.bindViewPager(vp_banner, ll_banner)
        }

        holder.itemView.tv_more.setOnClickListener {
            holder.itemView.context.startActivity<InformationActivity>()
        }
    }

    class MainHeaderViewHolder(itemView: View) : BaseViewHolder(itemView)

}
