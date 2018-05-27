package com.jcfy.xkt.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.Utils
import com.jcfy.xkt.api.MainApi
import com.jcfy.xkt.base.BaseListFragment
import com.jcfy.xkt.base.LoadListData
import com.jcfy.xkt.module.BannerWrapper
import com.jcfy.xkt.module.Information
import com.jcfy.xkt.page
import com.jcfy.xkt.ui.multitype.MainHeaderItemViewBinder
import com.jcfy.xkt.ui.multitype.MainInformationItemViewBinder
import com.kingja.loadsir.callback.Callback
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.view.RefreshListener
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_main.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class MainFragment : BaseListFragment(), RefreshListener, Callback.OnReloadListener, LoadListData {

    override fun loadData() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createViewByLoadSir(inflater.inflate(R.layout.fragment_main, container, false))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter.register(BannerWrapper::class, MainHeaderItemViewBinder())
        mAdapter.register(Information::class, MainInformationItemViewBinder())
        rv_main.layoutManager = LinearLayoutManager(context)
        rv_main.setHasFixedSize(true);
        srl_main.setRefreshListener(this)
        rv_main.adapter = mAdapter
        loadListData()
    }


    override fun loadListData(isInitialize: Boolean, isRefresh: Boolean) {
        val userApi = Api.createApi(MainApi::class)
        Utils.handleListData(userApi.getMainData(page = mPage.toString()), isInitialize, srl_main, this, mScopeProvider, Consumer {
            mLoadService.showSuccess()
            if (isRefresh) {
                mItems.clear()
                mItems.add(BannerWrapper(it.bannerList))
                mItems.addAll(it.resultList)
            } else {
                mItems.addAll(it.resultList)
            }
            mAdapter.notifyDataSetChanged()
        })
    }


    override fun refresh(isRefresh: Boolean) {
        mPage = mPage.page(isRefresh)
        loadListData(false, isRefresh)
    }

    override fun onReload(v: View?) {
        srl_main.refreshing()
    }

    companion object {
        fun newInstance(): MainFragment {
            val args = Bundle()
            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }

}

