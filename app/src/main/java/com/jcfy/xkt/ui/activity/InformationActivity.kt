package com.jcfy.xkt.ui.activity

import android.os.Bundle
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutManager
import com.jcfy.xkt.*
import com.jcfy.xkt.api.MainApi
import com.jcfy.xkt.base.BaseListActivity
import com.jcfy.xkt.base.LoadListData
import com.jcfy.xkt.module.Column
import com.jcfy.xkt.module.Information
import com.jcfy.xkt.ui.multitype.InformationColumnItemViewBinder
import com.jcfy.xkt.ui.multitype.InformationItemViewBinder
import com.jcfy.xkt.utils.selection.ImageViewSelectionBinder
import com.jcfy.xkt.utils.selection.SelectionAdapter
import com.jcfy.xkt.utils.selection.TextViewSelectionBinder
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.view.RefreshListener
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_information.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class InformationActivity : BaseListActivity(), RefreshListener, LoadListData {

    private var mSelectDictId = 1

    private val mSelectionAdapter: SelectionAdapter = SelectionAdapter().apply {
        register(AppCompatTextView::class.java, TextViewSelectionBinder())
        register(AppCompatImageView::class.java, ImageViewSelectionBinder())
        singleSelection()
        setListener { index, _ ->
            mPage = 1
            v_divider.bindBoolean2Visibility(index != 3)
            if (index == 3) {
                loadColumnListData()
                return@setListener
            }
            mSelectDictId = index + 1
            loadListData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        setTitleText("资讯")
        mAdapter.register(Information::class, InformationItemViewBinder())
        mAdapter.register(Column::class, InformationColumnItemViewBinder())
        rv_information.layoutManager = LinearLayoutManager(this)
        rv_information.adapter = mAdapter
        srl_information.setRefreshListener(this)
        mSelectionAdapter.bindLayout(ll_type, 0)
        loadListData()
    }

    override fun loadListData(isInitialize: Boolean, isRefresh: Boolean) {
        val api = Api.createApi(MainApi::class)
        Utils.handleListData(api.getInformationList(mSelectDictId.toString(), mPage.toString()), isInitialize, srl_information, this, mScopeProvider, Consumer {
            mLoadService.showSuccess()
            mItems.addPageList(isRefresh, it.newsList)
            mAdapter.notifyDataSetChanged()
        })
    }

    private fun loadColumnListData(isInitialize: Boolean = true, isRefresh: Boolean = true) {
        val api = Api.createApi(MainApi::class)
        Utils.handleListData(api.getColumnsList(mPage.toString()), isInitialize, srl_information, this, mScopeProvider, Consumer {
            mLoadService.showSuccess()
            mItems.addPageList(isRefresh, it.columnList)
            mAdapter.notifyDataSetChanged()
        })
    }

    override fun refresh(isRefresh: Boolean) {
        mPage = mPage.page(isRefresh)
        if (mSelectionAdapter.selectionIndex == 3)
            loadColumnListData(false, isRefresh)
        else
            loadListData(false, isRefresh)
    }

}