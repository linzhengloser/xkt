package com.jcfy.xkt.ui.itemdecoration

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jcfy.xkt.dp

/**
 * @author linzheng
 */
class MyGridItemDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        val position = parent!!.getChildLayoutPosition(view)
        val padding = 13.dp()
        val paddingHalf = padding.shr(1)
        outRect?.bottom = padding
        outRect?.right = if (position % 2 == 1) padding else paddingHalf
        outRect?.left = if (position % 2 == 1) paddingHalf else padding
    }

}