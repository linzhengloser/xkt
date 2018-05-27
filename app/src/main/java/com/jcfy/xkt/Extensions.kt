package com.jcfy.xkt

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.jcfy.xkt.api.*
import com.jcfy.xkt.module.question.Options
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
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.textColor

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

inline fun Activity.setMenuImage(imageResId: Int) {
    iv_menu?.visibility = View.VISIBLE
    iv_menu?.imageResource = imageResId
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


fun TextView.bindQuestionType(type: Int) {
    var backgroundResId: Int = when (type) {
        QUESTION_TYPE_SINGLE -> {
            text = "单选"
            R.drawable.shape_question_single_selection
        }
        QUESTION_TYPE_MULTI -> {
            text = "多选"
            R.drawable.shape_question_multi_selection
        } //多选
        QUESTION_TYPE_JUDGMENT -> {
            text = "判断"
            R.drawable.shape_question_judgment //判断
        }
        else -> R.drawable.shape_question_judgment
    }
    setBackgroundResource(backgroundResId)
}

// 题目相关
val answerOptionsString = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")

fun LinearLayout.bindQuestionOptions(optionsList: List<Options>) {
    var layout: LinearLayout
    var optionLetter: TextView
    var optionText: TextView
    optionsList.forEachIndexed { index, options ->
        layout = LinearLayout(context).apply {
            optionLetter = TextView(context).apply {
                layoutParams = LinearLayout.LayoutParams(21.dp(), 21.dp())
                text = answerOptionsString[options.tab]
                textSize = 16F
                gravity = Gravity.CENTER
                textColor = Color.parseColor("#262a3b")
                setBackgroundResource(R.drawable.shape_question_option_normal)
            }
            optionText = TextView(context).apply {
                layoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                text = options.content
                textSize = 16F
                textColor = Color.parseColor("#262a3b")
                setPadding(20.dp(), 0, 0, 0)
            }
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                gravity = Gravity.CENTER_VERTICAL
            }
            if (index > 0) setPadding(0, 21.dp(), 0, 0)
            orientation = LinearLayout.HORIZONTAL
            addView(optionLetter)
            addView(optionText)
        }
        addView(layout)
    }
}


inline fun Int.page(isRefresh: Boolean) = if (isRefresh) 1 else plus(1)

inline fun View.bindBoolean2Visibility(isVisibility: Boolean) = if (isVisibility) visibility = View.VISIBLE else View.GONE

object Utils {

    fun <T> handleListData(
            observable: Observable<Response<T>>,
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
