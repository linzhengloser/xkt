package com.jcfy.xkt

import android.app.Activity
import android.support.v4.content.ContextCompat
import android.view.View
import com.lz.baselibrary.LibraryApplication
import kotlinx.android.synthetic.main.layout_title.*
import me.drakeet.multitype.Items

/**
 * @author linzheng
 */

/**
 * 设置 Title
 */
inline fun Activity.setTitleText(title: String) {
    tv_title?.text = title
}

/**
 * 隐藏 Back 键
 */
inline fun Activity.goneBack() {
    iv_back?.visibility = View.GONE
}


/**
 * 像素转 DP
 */
inline fun Int.dp(): Int
        = (LibraryApplication.getInstance().resources.displayMetrics.density * this).toInt()

/**
 * Activity 获取 Color
 */
inline fun Activity.getResourceColor(color: Int) = ContextCompat.getColor(this,color)


/**
 * 添加测试数据
 */
fun Items.addTestData(clazz: Class<out Any>, itemCount: Int = 20){
    val list = mutableListOf<Any>()
    repeat(itemCount) {
        list.add(clazz.newInstance())
    }
    addAll(list)
}