package com.jcfy.xkt.ui.activity

import android.os.Bundle
import com.jcfy.xkt.R
import com.jcfy.xkt.androidScheduler
import com.jcfy.xkt.setTitleText
import com.lz.baselibrary.utils.ToastUtils
import com.uber.autodispose.kotlin.autoDisposable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_examination.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author linzheng
 */
class ExaminationActivity : QuestionActivity() {

    private val mSimpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("GMT+0")
    }

    private val mTimeLeft = 30 * 60 * 1000

    private lateinit var mCountDownDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        mContentViewLayoutId = R.layout.activity_examination
        mIsShowAnswer = false
        super.onCreate(savedInstanceState)
        setTitleText("考试")
        startCountDown()
    }

    /**
     * 考试倒计时
     */
    private fun startCountDown() {
        mCountDownDisposable = Observable.interval(0, 1, TimeUnit.MILLISECONDS)
                .observeOn(androidScheduler)
                .autoDisposable(mScopeProvider)
                .subscribe {
                    if (it >= mTimeLeft) {
                        ToastUtils.showToast("时间到！正在提交您的答案。")
                        return@subscribe
                    }
                    tv_countdown.text = mSimpleDateFormat.format(mTimeLeft - it)
                }
    }

}
