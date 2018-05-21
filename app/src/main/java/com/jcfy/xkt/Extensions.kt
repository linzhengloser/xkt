package com.jcfy.xkt

import android.app.Activity
import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.api.ApiConsumer
import com.jcfy.xkt.api.BaseData
import com.jcfy.xkt.utils.ApiFunction
import com.lz.baselibrary.LibraryApplication
import com.lz.baselibrary.base.BaseView
import com.lz.baselibrary.view.RefreshLayout
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.kotlin.autoDisposable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.layout_title.*
import me.drakeet.multitype.Items

/**
 * @author linzheng
 */

val androidScheduler: Scheduler = AndroidSchedulers.mainThread()

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
inline fun Int.dp(): Int = (LibraryApplication.getInstance().resources.displayMetrics.density * this).toInt()

/**
 * Activity 获取 Color
 */
inline fun Context.getResourceColor(color: Int) = ContextCompat.getColor(this, color)


/**
 * 添加测试数据
 */
fun Items.addTestData(clazz: Class<out Any>, itemCount: Int = 20) {
    val list = mutableListOf<Any>()
    repeat(itemCount) {
        list.add(clazz.newInstance())
    }
    addAll(list)
}

/**
 * 添加分页数据
 */
inline fun Items.addPageList(isRefresh: Boolean, list: List<Any>) {
    if (isRefresh && size > 0) clear()
    addAll(list)
}

inline fun Items.addPageData(isRefresh: Boolean, item: Any) {
    if (isRefresh && size > 0) clear()
    add(item)
}


inline fun Int.page(isRefresh: Boolean) = if (isRefresh) 1 else plus(1)

inline fun View.bindBoolean2Visibility(isVisibility: Boolean) = if (isVisibility) visibility = View.VISIBLE else View.GONE

object Utils {

    fun <T> handleListData(
            observable: Observable<BaseData<T>>,
            isInitialize: Boolean,
            refreshLayout: RefreshLayout,
            baseView: BaseView,
            scopeProvider: AndroidLifecycleScopeProvider,
            subscribe: Consumer<T>
    ) {
        observable.observeOn(androidScheduler)
                .doFinally { refreshLayout.refreshComplete() }
                .doOnSubscribe { if (isInitialize) baseView.showLoadingLayout() }
                .map(ApiFunction())
                .autoDisposable(scopeProvider)
                .subscribe(subscribe, ApiConsumer().bind(refreshLayout, baseView, isInitialize))
    }

    fun findNeedRegisterView(rootView: View): View? {
        for (i in 0..(rootView as ViewGroup).childCount) {
            if (rootView.getChildAt(i) is RefreshLayout) {
                return (rootView.getChildAt(i) as ViewGroup).getChildAt(1)
            }
        }
        return null
    }

}
