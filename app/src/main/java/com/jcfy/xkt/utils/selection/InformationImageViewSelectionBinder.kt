package com.jcfy.xkt.utils.selection

import android.support.v7.widget.AppCompatImageView
import com.jcfy.xkt.R
import com.jcfy.xkt.utils.Selector
import org.jetbrains.anko.imageResource

/**
 * @author linzheng
 */
class ImageViewSelectionBinder : SelectionBinder<AppCompatImageView> {

    private val mSelectionImageList = listOf(
            Selector(R.drawable.information_popular_normal, R.drawable.information_popular_press),
            Selector(R.drawable.information_new_normal, R.drawable.information_new_press),
            Selector(R.drawable.information_test_site_normal, R.drawable.information_test_site_press),
            Selector(R.drawable.information_columns_normal, R.drawable.information_columns_press)
    )

    override fun toggleView(index: Int, toggle: Boolean, view: AppCompatImageView) {
        view.imageResource = mSelectionImageList[index].get(toggle)
    }

}
