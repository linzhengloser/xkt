package com.jcfy.xkt.ui.activity

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jcfy.xkt.R
import com.jcfy.xkt.androidScheduler
import com.jcfy.xkt.api.ApiConsumer
import com.jcfy.xkt.api.MineApi
import com.jcfy.xkt.base.BaseListActivity
import com.jcfy.xkt.base.LoadListData
import com.jcfy.xkt.dp
import com.jcfy.xkt.module.mine.Recharge
import com.jcfy.xkt.module.mine.RechargeWrapper
import com.jcfy.xkt.setTitleText
import com.jcfy.xkt.ui.multitype.RechargeContentItemViewBinder
import com.jcfy.xkt.ui.multitype.RechargeHeaderItemViewBinder
import com.jcfy.xkt.ui.multitype.RechargeSubmitItemViewBinder
import com.jcfy.xkt.utils.ApiFunction
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.view.RefreshListener
import com.uber.autodispose.kotlin.autoDisposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_recharge_center.*
import me.drakeet.multitype.register
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.collections.forEachWithIndex

/**
 * @author linzheng
 */

class RechargeCenterActivity : BaseListActivity(), LoadListData, RefreshListener {

    // 题库类型 1 初级 2 中级
    private var mType = 1

    private lateinit var mRechargeWrapper: RechargeWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recharge_center)
        setTitleText("充值中心")

        mAdapter.register(Recharge::class, RechargeContentItemViewBinder())
        mAdapter.register(String::class)
                .to(RechargeHeaderItemViewBinder(), RechargeSubmitItemViewBinder())
                .withLinker({ _, s: String -> if (s == "Header") 0 else 1 })

        val gridLayoutManager = GridLayoutManager(this, 2)
        rv_recharge_center.layoutManager = gridLayoutManager
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (mItems[position] is String) {
                    return 2
                }
                return 1
            }
        }
        rv_recharge_center.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                val position = parent?.getChildLayoutPosition(view)
                if (mItems[position!!] is Recharge) {
                    val padding = 18.dp()
                    val paddingHalf = padding.shr(1)
                    val item = mItems[position] as Recharge
                    outRect?.left = if (item.isOddNumber) paddingHalf else padding
                    outRect?.right = if (item.isOddNumber) padding else paddingHalf
                    outRect?.bottom = padding
                }
            }
        })
        srl_recharge_center.isLoadMoreEnable(false)
        srl_recharge_center.setRefreshListener(this)
        rv_recharge_center.adapter = mAdapter
        loadListData()
    }

    override fun loadListData(isInitialize: Boolean, isRefresh: Boolean) {
        val api = Api.createApi(MineApi::class)
        api.getRechargeCenter()
                .map(ApiFunction())
                .observeOn(androidScheduler)
                .autoDisposable(mScopeProvider)
                .subscribe(Consumer { it ->
                    showSuccessLayout()
                    mRechargeWrapper = it
                    changeType()
                }, ApiConsumer().bind(srl_recharge_center, this, isInitialize))
    }

    private fun changeType() {
        mItems.clear()
        mItems.add("Header")
        if (mType == 0) {
            addRechargeItem(mRechargeWrapper.rechargeA)
        } else {
            addRechargeItem(mRechargeWrapper.rechargeB)
        }
        mItems.add("Footer")
        mAdapter.notifyDataSetChanged()
    }

    private fun addRechargeItem(list: List<Recharge>) {
        list.forEachWithIndex { i: Int, recharge: Recharge ->
            recharge.isOddNumber = i % 2 != 0
            mItems.add(recharge)
        }
    }

    override fun refresh(isRefresh: Boolean) {
        loadListData(false, isRefresh)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    public fun onMessageEvent(event: String) {
        if (event == "submit") {

        } else if (event == "changeType") {

        }
    }

}