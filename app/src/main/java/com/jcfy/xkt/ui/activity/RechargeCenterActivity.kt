package com.jcfy.xkt.ui.activity

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseListActivity
import com.jcfy.xkt.dp
import com.jcfy.xkt.module.RechargeContent
import com.jcfy.xkt.module.RechargeTypeName
import com.jcfy.xkt.setTitleText
import com.jcfy.xkt.ui.multitype.RechargeContentItemViewBinder
import com.jcfy.xkt.ui.multitype.RechargeSubmitItemViewBinder
import com.jcfy.xkt.ui.multitype.RechargeTypeNameItemViewBinder
import kotlinx.android.synthetic.main.activity_recharge_center.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */

class RechargeCenterActivity : BaseListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recharge_center)
        setTitleText("充值中心")

        mAdapter.register(RechargeTypeName::class, RechargeTypeNameItemViewBinder())
        mAdapter.register(RechargeContent::class, RechargeContentItemViewBinder())
        mAdapter.register(String::class, RechargeSubmitItemViewBinder())

        mItems.add(RechargeTypeName("题库类型"))
        mItems.add(RechargeContent().setContent("初级").setType(true))
        mItems.add(RechargeContent().setContent("中级").setType(true).setOddNumber(true))
        mItems.add(RechargeTypeName("题库类型", true))
        mItems.add(RechargeContent().setContent("初级"))
        mItems.add(RechargeContent().setContent("中级").setOddNumber(true))
        mItems.add(RechargeContent().setContent("初级"))
        mItems.add(RechargeContent().setContent("中级").setOddNumber(true))
        mItems.add(RechargeContent().setContent("初级"))
        mItems.add(RechargeContent().setContent("中级").setOddNumber(true))
        mItems.add("")

        val gridLayoutManager = GridLayoutManager(this, 2)
        rv_recharge_center.layoutManager = gridLayoutManager
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (mItems[position] is RechargeTypeName || mItems[position] is String) {
                    return 2
                }
                return 1
            }
        }
        rv_recharge_center.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                val position = parent?.getChildLayoutPosition(view)
                if (mItems[position!!] is RechargeContent) {
                    val padding = 18.dp()
                    val paddingHalf = padding.shr(1)
                    val item = mItems[position] as RechargeContent
                    outRect?.left = if (item.isOddNumber) paddingHalf else padding
                    outRect?.right = if (item.isOddNumber) padding else paddingHalf
                    outRect?.bottom = padding
                }
            }
        })

        rv_recharge_center.adapter = mAdapter
    }

}