package com.jcfy.xkt.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jcfy.xkt.R
import com.jcfy.xkt.addTestData
import com.jcfy.xkt.base.BaseListActivity
import com.jcfy.xkt.dp
import com.jcfy.xkt.getResourceColor
import com.jcfy.xkt.module.RechargeRecord
import com.jcfy.xkt.ui.multitype.RechargeRecordItemViewBinder
import com.lz.baselibrary.view.VerticalItemDecoration
import kotlinx.android.synthetic.main.activity_recharge_record.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class RechargeRecordActivity : BaseListActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recharge_record)

        srl_recharge_record.isLoadMoreEnable(false)

        mItems.addTestData(RechargeRecord::class.java)
        mAdapter.register(RechargeRecord::class,RechargeRecordItemViewBinder())

        rv_recharge_record.layoutManager = LinearLayoutManager(this)
        rv_recharge_record.addItemDecoration(VerticalItemDecoration(1.dp(),getResourceColor(R.color.itemDividerColor)))
        rv_recharge_record.adapter = mAdapter

    }

}
