package com.jcfy.xkt.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import com.jcfy.xkt.*
import com.jcfy.xkt.api.ApiConsumer
import com.jcfy.xkt.api.ExerciseApi
import com.jcfy.xkt.utils.ApiFunction
import com.jcfy.xkt.utils.Selector
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.utils.ToastUtils
import com.uber.autodispose.kotlin.autoDisposable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.layout_title.*
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.TimeUnit

/**
 * @author linzheng
 */

class ExerciseActivity : QuestionActivity(), View.OnClickListener {

    private lateinit var mAutomaticDisposable: Disposable

    override val mType: Int
        get() = 0

    override val mContentViewLayoutId: Int
        get() = R.layout.activity_exercise

    override fun onCreate(savedInstanceState: Bundle?) {
        mIsShowAnswer = true
        super.onCreate(savedInstanceState)
        setTitleText("练习")
        setMenuImage(R.drawable.exercise_question_view_answer)
        iv_menu.tag = "open"
        //收藏
        iv_collection.setOnClickListener {
            collectionQuestion()
        }
        iv_automatic.setOnClickListener(this)
        iv_menu.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when (v?.id) {
            R.id.iv_automatic -> {
                val toggle = toggleToolBarButton(v as ImageButton, mToolBarButtonSelectors["automatic"]!!)
                if (toggle) {
                    mAutomaticDisposable = Observable.interval(10, TimeUnit.SECONDS)
                            .observeOn(androidScheduler)
                            .autoDisposable(mScopeProvider)
                            .subscribe {
                                if (vp_menu.currentItem < mQuestionWrapper.questionList.size - 1)
                                    vp_menu.currentItem = vp_menu.currentItem + 1
                                else {
                                    ToastUtils.showToast("已是最后一道题！")
                                    toggleToolBarButton(v, mToolBarButtonSelectors["automatic"]!!)
                                    mAutomaticDisposable.dispose()
                                }
                            }
                } else {
                    mAutomaticDisposable.dispose()
                }
            }
            R.id.iv_menu -> {
                toggleToolBarButton(iv_menu, mToolBarButtonSelectors["showAnswer"]!!)
                mQuestionRecordList.forEach {
                    it.isShowAnswer = !it.isShowAnswer
                }
                EventBus.getDefault().post("toggleShowAnswer")
            }
        }
    }

    private fun toggleToolBarButton(view: ImageView, selector: Selector<Int>): Boolean {
        val tag = if (view.tag == "close") "open" else "close"
        val toggle = tag == "open"
        view.tag = tag
        view.setImageResource(selector.get(toggle))
        return toggle
    }

    override fun invalidateToolBar() {
        super.invalidateToolBar()
        tv_total_question.text = "${this.mCurrentQuestionIndex + 1}/${mQuestionWrapper.questionCount}"
        tv_right_question.text = mRightQuestionCount.toString()
        tv_wrong_question.text = mWrongQuestionCount.toString()
        tv_last_question.text = if (vp_menu.currentItem == 0) "无" else "上一题"
        tv_last_question.isClickable = vp_menu.currentItem != 0
        tv_next_question.text = if (vp_menu.currentItem == mFragmentList.size - 1) "提交" else "下一题"
        tv_last_question.isClickable = vp_menu.currentItem != mFragmentList.size - 1
        val currentQuestion = mQuestionWrapper.questionList[vp_menu.currentItem]
        iv_collection.bindCollection(currentQuestion.isCollection)
        iv_collection.tag = if (currentQuestion.isCollection == 1) "open" else "close"
    }

    override fun onBackPressed() {
        saveQuestionRecord()
    }

    /**
     * 保存做题记录
     */
    private fun saveQuestionRecord() {
        super.onBackPressed()
    }

    /**
     * 收藏题目
     */
    private fun collectionQuestion() {
        val api = Api.createApi(ExerciseApi::class)
        val currentQuestion = mQuestionWrapper.questionList[vp_menu.currentItem]
        api.collectionQuestion(currentQuestion.questionId)
                .doFinally { hideLoadingDialog() }
                .doOnSubscribe { showLoadingDialog() }
                .map(ApiFunction())
                .observeOn(androidScheduler)
                .autoDisposable(mScopeProvider)
                .subscribe(Consumer {
                    if (it.flag == 3 || it.flag == 1) {
                        val isCollection = toggleToolBarButton(iv_collection, mToolBarButtonSelectors["collection"]!!)
                        currentQuestion.isCollection = if (isCollection) 1 else 2
                        ToastUtils.showToast("${if (!isCollection) "取消收藏" else "收藏"}成功")
                    } else {
                        ToastUtils.showToast("${if (it.flag == 2) "取消收藏" else "收藏"}失败")
                    }
                }, ApiConsumer())
    }

    /**
     * 保存练习记录
     */
    override fun finish() {
        if (mWrongQuestionCount == 0 && mRightQuestionCount == 0)
            super.finish()

        val api = Api.createApi(ExerciseApi::class)
        api.saveDoQuestionRecord("1", "2,3,4")
                .map(ApiFunction())
                .doFinally { hideLoadingDialog() }
                .doOnSubscribe { showLoadingDialog() }
                .observeOn(androidScheduler)
                .autoDisposable(mScopeProvider)
                .subscribe(Consumer {
                    ToastUtils.showToast("练习记录保存成功")
                    super.finish()
                }, Consumer {
                    ToastUtils.showToast("练习记录保存失败")
                    super.finish()
                })
    }

}
