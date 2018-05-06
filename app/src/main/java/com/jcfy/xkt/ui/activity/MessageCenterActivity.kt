package com.jcfy.xkt.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jcfy.xkt.*
import com.jcfy.xkt.base.BaseListActivity
import com.jcfy.xkt.module.Message
import com.jcfy.xkt.ui.multitype.MessageCenterItemViewBinder
import com.lz.baselibrary.view.VerticalItemDecoration
import kotlinx.android.synthetic.main.activity_message_center.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class MessageCenterActivity : BaseListActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_center)

        setTitleText("消息中心")

        mAdapter.register(Message::class, MessageCenterItemViewBinder())
        srl_message_center.isLoadMoreEnable(false)
        rv_message_center.layoutManager = LinearLayoutManager(this)
        rv_message_center.addItemDecoration(VerticalItemDecoration(1.dp(), getResourceColor(R.color.itemDividerColor)))
        mItems.addTestData(Message::class.java)
        rv_message_center.adapter = mAdapter
    }
}
