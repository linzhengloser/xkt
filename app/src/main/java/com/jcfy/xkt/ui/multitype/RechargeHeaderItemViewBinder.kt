package com.jcfy.xkt.ui.multitype

import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.utils.selection.RechargeTypeSelectionBinder
import com.jcfy.xkt.utils.selection.SelectionAdapter
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_recharge_header.view.*
import me.drakeet.multitype.ItemViewBinder
import org.greenrobot.eventbus.EventBus

/**
 * @author linzheng
 */
class RechargeHeaderItemViewBinder : ItemViewBinder<String, RechargeHeaderItemViewBinder.RechargeHeaderViewHolder>() {

    val mSelectionAdapter: SelectionAdapter by lazy {
        SelectionAdapter().apply {
            singleSelection()
            register(AppCompatTextView::class.java, RechargeTypeSelectionBinder())
            setListener { _, _ ->
                EventBus.getDefault().post("changeType")
            }
        }
    }


    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = RechargeHeaderViewHolder(inflater.inflate(R.layout.item_recharge_header, parent, false))

    override fun onBindViewHolder(holder: RechargeHeaderViewHolder, item: String) {
    }

    inner class RechargeHeaderViewHolder(itemView: View) : BaseViewHolder(itemView) {
        init {
            mSelectionAdapter.bindLayout(itemView.ll_type, 0)
        }
    }

}