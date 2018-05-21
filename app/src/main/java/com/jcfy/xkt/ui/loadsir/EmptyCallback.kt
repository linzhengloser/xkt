package com.jcfy.xkt.ui.loadsir

import android.content.Context
import android.view.View
import com.jcfy.xkt.R
import com.kingja.loadsir.callback.Callback

/**
 * @author linzheng
 */
class EmptyCallback : Callback() {
    override fun onCreateView() = R.layout.layout_empty

    override fun onReloadEvent(context: Context?, view: View?): Boolean {

        return true
    }

}
