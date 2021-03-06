package com.jcfy.xkt.base

import android.view.View
import com.jcfy.xkt.Utils
import com.jcfy.xkt.ui.loadsir.EmptyCallback
import com.jcfy.xkt.ui.loadsir.LoadingCallback
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.lz.baselibrary.base.LibraryBaseListFragment

/**
 * @author linzheng
 */
abstract class BaseListFragment : LibraryBaseListFragment(), Callback.OnReloadListener {

    protected lateinit var mLoadService: LoadService<Any>

    protected fun createViewByLoadSir(rootView: View): View {
        mLoadService = LoadSir.getDefault().register(Utils.findNeedRegisterView(rootView)!!, this)
        return rootView
    }

    override fun showLoadingLayout() {
        mLoadService.showCallback(LoadingCallback::class.java)
    }

    override fun showEmptyDataLayout() {
        super.showEmptyDataLayout()
        mLoadService.showCallback(EmptyCallback::class.java)
    }

    override fun showSuccessLayout() {
        mLoadService.showSuccess()
    }

    override fun loadData() {
    }

    override fun onReload(v: View?) {
    }
}
