package com.jcfy.xkt.base

import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.jcfy.xkt.Utils
import com.jcfy.xkt.ui.loadsir.EmptyCallback
import com.jcfy.xkt.ui.loadsir.ErrorCallback
import com.jcfy.xkt.ui.loadsir.LoadingCallback
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.lz.baselibrary.base.LibraryBaseListActivity

/**
 * @author linzheng
 */
open class BaseListActivity : LibraryBaseListActivity(), Callback.OnReloadListener {

    protected lateinit var mLoadService: LoadService<Any>

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        val rootView = findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT).getChildAt(0)
        mLoadService = LoadSir.getDefault().register(Utils.findNeedRegisterView(rootView), this)
    }

    override fun showLoadingLayout() {
        super.showLoadingLayout()
        mLoadService.showCallback(LoadingCallback::class.java)
    }

    override fun showEmptyDataLayout() {
        super.showEmptyDataLayout()
        mLoadService.showCallback(EmptyCallback::class.java)
    }

    override fun showErrorLayout() {
        mLoadService.showCallback(ErrorCallback::class.java)
    }

    override fun showSuccessLayout() {
        super.showSuccessLayout()
        mLoadService.showSuccess()
    }

    override fun onReload(v: View?) {
    }
}
