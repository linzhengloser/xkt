package com.jcfy.xkt.base

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.jcfy.xkt.ui.loadsir.EmptyCallback
import com.jcfy.xkt.ui.loadsir.ErrorCallback
import com.jcfy.xkt.ui.loadsir.LoadingCallback
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.lz.baselibrary.base.LibraryBaseActivity
import kotlinx.android.synthetic.main.layout_title.*


/**
 * @author linzheng
 */
open class BaseActivity : LibraryBaseActivity(), Callback.OnReloadListener {

    private lateinit var mLoadService: LoadService<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iv_back?.setOnClickListener {
            finish()
        }
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
//        var childCount = (findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT).getChildAt(0) as ViewGroup).childCount
//        mLoadService = LoadSir.getDefault().register(this, this)
    }

    protected fun setTranslucentStatus(on: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val win = window
            val winParams = win.attributes
            val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            if (on) {
                winParams.flags = winParams.flags or bits
            } else {
                winParams.flags = winParams.flags and bits.inv()
            }
            win.attributes = winParams
        }
    }


    override fun showLoadingLayout() {
        mLoadService.showCallback(LoadingCallback::class.java)
    }

    override fun showEmptyDataLayout() {
        mLoadService.showCallback(EmptyCallback::class.java)
    }

    override fun showErrorLayout() {
        mLoadService.showCallback(ErrorCallback::class.java)
    }

    override fun onReload(v: View?) {

    }

}

