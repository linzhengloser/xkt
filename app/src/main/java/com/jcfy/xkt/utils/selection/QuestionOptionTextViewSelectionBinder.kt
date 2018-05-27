package com.jcfy.xkt.utils.selection

import android.graphics.Color
import android.widget.TextView
import com.jcfy.xkt.R
import com.jcfy.xkt.utils.Selector
import org.jetbrains.anko.textColor

/**
 * @author linzheng
 */
class QuestionOptionTextViewSelectionBinder : SelectionBinder<TextView> {

    private val mSelectionTextColor = Selector(Color.parseColor("#333333"), Color.WHITE)

    private val mSelectionBackgroundResId = Selector(
            R.drawable.shape_question_option_normal,
            R.drawable.shape_question_option_press
    )

    override fun needSelectionChildIndex(): ArrayList<Int> {
        return arrayListOf(0)
    }

    override fun toggleView(index: Int, toggle: Boolean, view: TextView) {
        view.apply {
            textColor = mSelectionTextColor.get(toggle)
            setBackgroundResource(mSelectionBackgroundResId.get(toggle))
        }
    }

}