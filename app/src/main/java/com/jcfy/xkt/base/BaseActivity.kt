package com.jcfy.xkt.base

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import com.lz.baselibrary.base.LibraryBaseActivity
import kotlinx.android.synthetic.main.layout_title.*


/**
 * @author linzheng
 */
open class BaseActivity :LibraryBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iv_back?.setOnClickListener {
            finish()
        }
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

}
