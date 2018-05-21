package com.jcfy.xkt.utils.selection

import android.graphics.Color
import android.support.v7.widget.AppCompatTextView
import com.jcfy.xkt.utils.Selector
import org.jetbrains.anko.textColor

/**
 * @author linzheng
 */
class TextViewSelectionBinder : SelectionBinder<AppCompatTextView> {

    private val mSelectionTextColor = Selector(Color.parseColor("#262a3b"), Color.parseColor("#4e9afd"))

    private val mSelectionTextSize = Selector(13F, 16F)

    override fun toggleView(index: Int, toggle: Boolean, view: AppCompatTextView) {
        view.apply {
            textSize = mSelectionTextSize.get(toggle)
            textColor = mSelectionTextColor.get(toggle)
        }
    }

}
