package com.jcfy.xkt

import android.app.Activity
import android.view.View
import kotlinx.android.synthetic.main.layout_title.*

/**
 * @author linzheng
 */

/**
 * 设置 Title
 */
inline fun Activity.setTitleText(title: String) {
    tv_title?.text = title
}

/**
 * 隐藏 Back 键
 */
inline fun Activity.goneBack(){
    iv_back?.visibility = View.GONE
}