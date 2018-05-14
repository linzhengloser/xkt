package com.jcfy.xkt.ui.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.jcfy.xkt.dp
import org.jetbrains.anko.collections.forEachWithIndex
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.textColor

/**
 * @author linzheng
 */
class BottomNavigationView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs), ViewPager.OnPageChangeListener {

    private val mItems = mutableListOf<NavigationItem>()

    var mNavigationImageViewWidth = 21.dp()


    //TODO 可以将此类中的部分逻辑封装成方法应用在不同的地方
    var mCurrentPressItemIndex = 0

     private var mBindViewPager: ViewPager? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        orientation = HORIZONTAL
    }

    fun setNavigationItems(items: List<NavigationItem>,viewPager: ViewPager? = null) {
        mItems.addAll(items)
        initNavigationItem()
        mBindViewPager = viewPager
        mBindViewPager?.addOnPageChangeListener(this)
    }

    private fun initNavigationItem() {
        mItems.forEachWithIndex { index, navigationItem ->
            addView(createNavigationItem(navigationItem, index))
        }
    }


    private fun setCurrentPressItem(index: Int) {
        if (index == mCurrentPressItemIndex) return
        getChildAt(mCurrentPressItemIndex).run {
            tag = ""
            setNavigationItemView(this as ViewGroup, mItems[mCurrentPressItemIndex], false)
        }
        mCurrentPressItemIndex = index
        getChildAt(mCurrentPressItemIndex).run {
            tag = "press"
            setNavigationItemView(this as ViewGroup, mItems[mCurrentPressItemIndex], true)
        }
    }

    private fun setNavigationItemView(itemView: ViewGroup, navigationItem: NavigationItem, isPress: Boolean) {
        val imageView: ImageView = itemView.getChildAt(0) as ImageView
        val textView: TextView = itemView.getChildAt(1) as TextView
        imageView.imageResource = if (isPress) navigationItem.pressIconResId else navigationItem.normalIconResId
        textView.textColor = if (isPress) navigationItem.pressTextColor else navigationItem.normalTextColor
        textView.text = if (isPress) navigationItem.pressText else navigationItem.normalText
    }

    private fun createNavigationItem(navigationItem: NavigationItem, index: Int): View {
        return LinearLayout(context).apply {
            layoutParams = LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT).apply {
                weight = 1f
            }
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(0, 6.dp(), 0, 0)

            val imageView = ImageView(context).apply {
                imageResource = if (index == 0) navigationItem.pressIconResId else navigationItem.normalIconResId
                layoutParams = LayoutParams(mNavigationImageViewWidth, mNavigationImageViewWidth)
            }

            val textView = TextView(context).apply {
                textSize = 12f
                text = navigationItem.normalText
                layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                        .apply {
                            topMargin = 2.dp()
                        }
            }
            setOnClickListener {
                setCurrentPressItem(index)
                mBindViewPager?.currentItem = index
            }
            addView(imageView)
            addView(textView)
        }
    }


    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        setCurrentPressItem(position)
    }
}
