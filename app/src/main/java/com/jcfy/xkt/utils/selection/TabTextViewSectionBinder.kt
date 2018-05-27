package com.jcfy.xkt.utils.selection

import android.graphics.Color
import android.support.v7.widget.AppCompatTextView
import com.jcfy.xkt.R
import com.jcfy.xkt.utils.Selector

/**
 * @author linzheng
 */
class TabTextViewSectionBinder : SelectionBinder<AppCompatTextView> {

    private val mLeftSelectionBackgroundResId = Selector(R.drawable.shape_common_left_tab_normal, R.drawable.shape_common_left_tab_press)

    private val mRightSelectionBackgroundResId = Selector(R.drawable.shape_common_right_tab_normal, R.drawable.shape_common_right_tab_press)

    private val mSelectionTextColor = Selector(Color.parseColor("#4e9afd"), Color.WHITE)

    override fun toggleView(index: Int, toggle: Boolean, view: AppCompatTextView) {
        val selector = if (index == 0) {
            mLeftSelectionBackgroundResId
        } else {
            mRightSelectionBackgroundResId
        }
        view.setBackgroundResource(selector.get(toggle))
        view.setTextColor(mSelectionTextColor.get(toggle))
    }
}