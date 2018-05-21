package com.jcfy.xkt.utils.selection

import android.view.View

/**
 * @author linzheng
 */
interface SelectionBinder<T : View> {

    fun toggleView(index: Int, toggle: Boolean, view: T)

}