package com.jcfy.xkt.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jcfy.xkt.*
import com.jcfy.xkt.api.MineApi
import com.jcfy.xkt.base.BaseListActivity
import com.jcfy.xkt.base.LoadListData
import com.jcfy.xkt.module.mine.Msg
import com.jcfy.xkt.ui.multitype.MessageCenterItemViewBinder
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.view.RefreshListener
import com.lz.baselibrary.view.VerticalItemDecoration
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_message_center.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class MessageCenterActivity : BaseListActivity(), LoadListData, RefreshListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_center)
        setTitleText("消息中心")
        mAdapter.register(Msg::class, MessageCenterItemViewBinder())
        srl_message_center.setRefreshListener(this)
        rv_message_center.layoutManager = LinearLayoutManager(this)
        rv_message_center.addItemDecoration(VerticalItemDecoration(1.dp(), getResourceColor(R.color.itemDividerColor)))
        rv_message_center.adapter = mAdapter
        loadListData()
    }

    override fun refresh(isRefresh: Boolean) {
        mPage = mPage.page(isRefresh)
        loadListData(false, isRefresh)
    }

    override fun loadListData(isInitialize: Boolean, isRefresh: Boolean) {
        val api = Api.createApi(MineApi::class)
        Utils.handleListData(api.getMessageCenter(page = mPage.toString()), isInitialize, srl_message_center, this, mScopeProvider, Consumer {
            showSuccessLayout()
            mItems.addPageList(isRefresh, it.msgList)
            mAdapter.notifyDataSetChanged()
        })
    }

}
