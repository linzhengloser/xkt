package com.jcfy.xkt.ui.activity

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.jcfy.xkt.R
import com.jcfy.xkt.androidScheduler
import com.jcfy.xkt.api.ExaminationApi
import com.jcfy.xkt.module.ExaminationResult
import com.jcfy.xkt.setTitleText
import com.jcfy.xkt.utils.ApiFunction
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.utils.ToastUtils
import com.uber.autodispose.kotlin.autoDisposable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_examination.*
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author linzheng
 */
class ExaminationActivity : QuestionActivity() {

    private val mExitAlertDialog by lazy {
        AlertDialog.Builder(this)
                .setTitle("警告")
                .setMessage("您还未提交答案，确定要退出吗？")
                .setNegativeButton("继续考试") { _: DialogInterface, i: Int -> }
                .setPositiveButton("退出") { _, _ -> super.finish() }
                .create()
    }

    private val mPostAlertDialog by lazy {
        AlertDialog.Builder(this)
                .setTitle("警告")
                .setMessage("您确定要提交答案吗？")
                .setNegativeButton("继续答题") { dialogInterface: DialogInterface, _ -> dialogInterface.dismiss() }
                .setPositiveButton("确定") { _, _ -> postAnswer() }
                .create()
    }

    private val mSimpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("GMT+0")
    }

    private val mTimeLeft = 30 * 60 * 1000

    private var mAlreadyUseTime = 0L

    override val mType: Int
        get() = 1

    // 0 初级 1 中级
    private var mQuestionType = 0

    override val mContentViewLayoutId: Int
        get() = R.layout.activity_examination

    private lateinit var mCountDownDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        mIsShowAnswer = false
        super.onCreate(savedInstanceState)
        mQuestionType = intent.getIntExtra("type", 0)
        setTitleText("考试")
        startCountDown()
        tv_submit.setOnClickListener {
            if (validateQuestion()) {
                ToastUtils.showToast("您还没有答题，不能提交")
                return@setOnClickListener
            }
            mPostAlertDialog.show()
        }
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
                    mAlreadyUseTime = it
                    tv_countdown.text = mSimpleDateFormat.format(mTimeLeft - it)
                }
    }

    override fun finish() {
        mExitAlertDialog.show()
    }

    /**
     * 提交答案
     */
    private fun postAnswer() {
        val api = Api.createApi(ExaminationApi::class)
        val achievement = mRightQuestionCount * 1.5F
        api.postAnswer(mQuestionType, achievement)
                .map(ApiFunction())
                .doOnSubscribe { showLoadingDialog() }
                .doFinally { hideLoadingDialog() }
                .observeOn(androidScheduler)
                .autoDisposable(mScopeProvider)
                .subscribe(Consumer {
                    val examinationResult = buildExaminationResult(achievement)
                    startActivity<ExaminationResultActivity>("result" to examinationResult)
                    super.finish()
                }, Consumer {
                    ToastUtils.showToast("答案提交失败")
                    super.finish()
                })
    }

    private fun buildExaminationResult(achievement: Float): ExaminationResult {
        return ExaminationResult(
                achievement,
                mQuestionWrapper.questionCount,
                mRightQuestionCount,
                mWrongQuestionCount,
                mTimeLeft.toLong(),
                mAlreadyUseTime
        )
    }
}
