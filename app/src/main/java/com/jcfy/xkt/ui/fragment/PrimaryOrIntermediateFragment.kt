package com.jcfy.xkt.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseListFragment
import com.lz.baselibrary.view.RefreshListener
import kotlinx.android.synthetic.main.fragment_schedule.*
import org.greenrobot.eventbus.EventBus

/**
 * @author linzheng
 */
//初级
const val TYPE_PRIMARY = 1
//中级
const val TYPE_INTERMEDIATE = 2
//刷新
const val EVENT_REFRESH = "refresh"

open class PrimaryOrIntermediateFragment : BaseListFragment(), RefreshListener {

    protected var mType = TYPE_PRIMARY

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createViewByLoadSir(inflater.inflate(R.layout.fragment_schedule, container, false))
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mType = arguments?.getInt("type")!!
        srl_schedule.isLoadMoreEnable(false)
        srl_schedule.setRefreshListener(this)
        rv_schedule.layoutManager = LinearLayoutManager(context)
        rv_schedule.adapter = mAdapter
    }

    override fun refresh(isRefresh: Boolean) {
        mItems.clear()
        EventBus.getDefault().post(EVENT_REFRESH)
    }

}