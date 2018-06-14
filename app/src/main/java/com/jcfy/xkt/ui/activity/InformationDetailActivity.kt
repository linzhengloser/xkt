package com.jcfy.xkt.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jcfy.xkt.*
import com.jcfy.xkt.api.LIMIT_DEFAULT_VALUE
import com.jcfy.xkt.api.MainApi
import com.jcfy.xkt.base.BaseListActivity
import com.jcfy.xkt.base.LoadListData
import com.jcfy.xkt.module.Information
import com.jcfy.xkt.module.InformationDetail
import com.jcfy.xkt.ui.multitype.InformationDetailItemViewBinder
import com.jcfy.xkt.ui.multitype.InformationItemViewBinder
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.view.RefreshListener
import com.lz.baselibrary.view.VerticalItemDecoration
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_information_detail.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class InformationDetailActivity : BaseListActivity(), LoadListData, RefreshListener {

    private lateinit var mInformationId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_detail)
        setTitleText("资讯")
        mInformationId = intent.getIntExtra("informationId", 0).toString()

        mAdapter.register(InformationDetail::class, InformationDetailItemViewBinder())
        mAdapter.register(Information::class, InformationItemViewBinder())

        srl_information_detail.setRefreshListener(this)
        srl_information_detail.isLoadMoreEnable(false)
        rv_information_detail.layoutManager = LinearLayoutManager(this)
        rv_information_detail.addItemDecoration(VerticalItemDecoration(1.dp(), getResourceColor(R.color.itemDividerColor)))
        rv_information_detail.adapter = mAdapter

        loadListData()
    }

    override fun loadListData(isInitialize: Boolean, isRefresh: Boolean) {
        val api = Api.createApi(MainApi::class)
        Utils.handleListData(api.getInformationDetail(mInformationId), isInitialize, srl_information_detail, this, mScopeProvider, Consumer {
            mLoadService.showSuccess()
            mItems.clear()
            mItems.add(it.newsDetail)
            if (it.recommendList.size < LIMIT_DEFAULT_VALUE.toInt())
                srl_information_detail.isLoadMoreEnable(false)
            mItems.addAll(it.recommendList)
            mAdapter.notifyDataSetChanged()
        })
    }

    override fun refresh(isRefresh: Boolean) {
        loadListData(false, isRefresh)
    }
}
