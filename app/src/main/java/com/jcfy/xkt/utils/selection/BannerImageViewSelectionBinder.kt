package com.jcfy.xkt.utils.selection

import android.support.v7.widget.AppCompatImageView
import com.jcfy.xkt.R
import com.jcfy.xkt.dp
import com.jcfy.xkt.utils.Selector
import org.jetbrains.anko.imageResource

/**
 * @author linzheng
 */
class BannerImageViewSelectionBinder : SelectionBinder<AppCompatImageView> {

    private val mBannerImageSelection: Selector<Int> by lazy {
        Selector(R.drawable.shape_banner_dot_normal, R.drawable.shape_banner_dot_press)
    }

    private val mBannerLayoutParamsSelection: Selector<Pair<Int, Int>>  by lazy {
        Selector(Pair(4.dp(), 4.dp()), Pair(6.dp(), 6.dp()))
    }

    override fun toggleView(index: Int, toggle: Boolean, view: AppCompatImageView) {
        view.apply {
            imageResource = mBannerImageSelection.get(toggle)
            layoutParams.width = mBannerLayoutParamsSelection.get(toggle).first
            layoutParams.height = mBannerLayoutParamsSelection.get(toggle).second
            layoutParams = layoutParams
        }
    }

}
