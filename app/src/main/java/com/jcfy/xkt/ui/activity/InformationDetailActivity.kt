package com.jcfy.xkt.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jcfy.xkt.R
import com.jcfy.xkt.addTestData
import com.jcfy.xkt.base.BaseListActivity
import com.jcfy.xkt.dp
import com.jcfy.xkt.getResourceColor
import com.jcfy.xkt.module.Information
import com.jcfy.xkt.module.InformationDetail
import com.jcfy.xkt.ui.multitype.InformationDetailItemViewBinder
import com.jcfy.xkt.ui.multitype.InformationItemViewBinder
import com.lz.baselibrary.view.VerticalItemDecoration
import kotlinx.android.synthetic.main.activity_information_detail.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class InformationDetailActivity : BaseListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_detail)

        mAdapter.register(InformationDetail::class, InformationDetailItemViewBinder())
        mAdapter.register(Information::class, InformationItemViewBinder())

        mItems.add(InformationDetail())
        mItems.addTestData(Information::class.java)

        srl_information_detail.isLoadMoreEnable(false)
        rv_information_detail.layoutManager = LinearLayoutManager(this)
        rv_information_detail.addItemDecoration(VerticalItemDecoration(1.dp(), getResourceColor(R.color.itemDividerColor)))
        rv_information_detail.adapter = mAdapter
    }

}
