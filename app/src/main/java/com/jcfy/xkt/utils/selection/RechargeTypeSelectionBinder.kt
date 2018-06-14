package com.jcfy.xkt.utils.selection

import android.graphics.Color
import android.support.v7.widget.AppCompatTextView
import com.jcfy.xkt.R
import com.jcfy.xkt.utils.Selector
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.textColor

/**
 * @author linzheng
 */
class RechargeTypeSelectionBinder : SelectionBinder<AppCompatTextView> {

    private val mSelectionBackgroundResId = Selector(
            R.drawable.shape_recharge_center_package_frame_normal,
            R.drawable.shape_recharge_center_package_frame_press
    )

    private val mSelectionTextColor = Selector(
            Color.parseColor("#4e9afd"),
            Color.WHITE
    )

    override fun toggleView(index: Int, toggle: Boolean, view: AppCompatTextView) {
        view.backgroundResource = mSelectionBackgroundResId.get(toggle)
        view.textColor = mSelectionTextColor.get(toggle)
    }

}