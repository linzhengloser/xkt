package com.jcfy.xkt.api

import com.lz.baselibrary.base.BaseView
import com.lz.baselibrary.utils.ToastUtils
import com.lz.baselibrary.view.RefreshLayout
import io.reactivex.functions.Consumer

/**
 * @author linzheng
 */

const val EMPTY_DATA = "200203"

class ApiConsumer : Consumer<Throwable> {

    private var mRefreshLayout: RefreshLayout? = null

    private var mBaseView: BaseView? = null

    private var mIsInitialize: Boolean? = null

    fun bind(refreshLayout: RefreshLayout?, baseView: BaseView, isInitialize: Boolean): ApiConsumer {
        this.mRefreshLayout = refreshLayout
        this.mBaseView = baseView
        this.mIsInitialize = isInitialize
        return this
    }

    override fun accept(t: Throwable) {
        if (t is ApiException) {
            if (t.errorCode == EMPTY_DATA) {
                mRefreshLayout?.isLoadMoreEnable(false)
                if (mIsInitialize == true) mBaseView?.showEmptyDataLayout()
            } else {
                ToastUtils.showToast("API异常。" + t.errorMessage)
            }
        } else {
            ToastUtils.showToast("程序内部异常。" + t.message)
        }
    }

}
