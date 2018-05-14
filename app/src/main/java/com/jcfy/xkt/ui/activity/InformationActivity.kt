package com.jcfy.xkt.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jcfy.xkt.R
import com.jcfy.xkt.addTestData
import com.jcfy.xkt.base.BaseListActivity
import com.jcfy.xkt.module.Information
import com.jcfy.xkt.ui.multitype.InformationItemViewBinder
import kotlinx.android.synthetic.main.activity_information.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class InformationActivity : BaseListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)


        srl_information.isLoadMoreEnable(false)

        mAdapter.register(Information::class, InformationItemViewBinder())
        mItems.addTestData(Information::class.java)

        rv_information.layoutManager = LinearLayoutManager(this)
        rv_information.adapter = mAdapter

    }

}