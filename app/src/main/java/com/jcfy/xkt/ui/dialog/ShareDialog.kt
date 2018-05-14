package com.jcfy.xkt.ui.dialog

import com.jcfy.xkt.R
import com.jcfy.xkt.dp
import com.lz.baselibrary.base.BaseDialogFragment

/**
 * @author linzheng
 */
class ShareDialog() : BaseDialogFragment() {
    override val layoutResID: Int = R.layout.dialog_share

    override fun setParams() {
        setWidth(300.dp())
        setHeight(182.dp())
    }

    override fun initViewsAndEvents() {

    }
}