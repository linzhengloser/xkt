package com.jcfy.xkt.ui.view

import android.graphics.Color

/**
 * @author linzheng
 */
data class NavigationItem(
        val normalTextColor: Int,
        val pressTextColor: Int,
        val normalIconResId: Int,
        val pressIconResId: Int,
        val normalText: String,
        val pressText: String
) {

    companion object Factory {

        fun createNavigationItem(normalIconResId: Int, pressIconResId: Int, text: String) = NavigationItem(
                Color.parseColor("#bfbfbf"),
                Color.parseColor("#4e9afd"),
                normalIconResId,
                pressIconResId,
                text,
                text
        )
    }

}
