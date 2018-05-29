package com.jcfy.xkt.ui.activity

import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import com.jcfy.xkt.*
import com.jcfy.xkt.api.ApiConsumer
import com.jcfy.xkt.api.ExerciseApi
import com.jcfy.xkt.base.BaseActivity
import com.jcfy.xkt.module.question.QuestionRecord
import com.jcfy.xkt.module.question.QuestionWrapper
import com.jcfy.xkt.ui.fragment.QuestionFragment
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

class ExerciseActivity : BaseActivity(), ViewPager.OnPageChangeListener, View.OnClickListener {

    private val mToolBarButtonSelectors = mapOf(
            "collection" to Selector(R.drawable.exercise_collection_normal, R.drawable.exercise_collection_press),
            "eyeProtectionMode" to Selector(R.drawable.exercise_eye_protection_mode_normal, R.drawable.exercise_eye_protection_mode_press),
            "automatic" to Selector(R.drawable.exercise_automatic_normal, R.drawable.exercise_automatic_press),
            "showAnswer" to Selector(R.drawable.exercise_question_not_view_answer, R.drawable.exercise_question_view_answer)
    )

    val mFragmentList = mutableListOf<QuestionFragment>()

    private lateinit var mQuestionWrapper: QuestionWrapper

    private var mQuestionRecordList = mutableListOf<QuestionRecord>()

    private var mCurrentQuestionIndex: Int = 0

    private var mRightQuestionCount: Int = 0

    private var mWrongQuestionCount: Int = 0

    private lateinit var mAutomaticDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        mQuestionWrapper = intent.getParcelableExtra("questionWrapper")
        this.mCurrentQuestionIndex = intent.getIntExtra("index", 0)

        setTitleText("练习")
        setMenuImage(R.drawable.exercise_question_view_answer)
        iv_menu.tag = "open"

        mQuestionWrapper.questionList.forEach {
            val questionRecord = QuestionRecord(it.questionId)
            mQuestionRecordList.add(questionRecord)
            mFragmentList.add(QuestionFragment.newInstance(it, questionRecord))
        }

        vp_exercise.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = mFragmentList[position]
            override fun getCount() = mFragmentList.size
        }

        vp_exercise.addOnPageChangeListener(this)

        // 上一题
        tv_last_question.setOnClickListener {
            vp_exercise.currentItem = vp_exercise.currentItem - 1
        }
        // 下一题
        tv_next_question.setOnClickListener {
            vp_exercise.currentItem = vp_exercise.currentItem + 1
        }
        //护眼模式
        iv_eye_protection_mode.setOnClickListener(this)

        //收藏
        iv_collection.setOnClickListener {
            collectionQuestion()
        }

        iv_automatic.setOnClickListener(this)
        iv_menu.setOnClickListener(this)

        vp_exercise.currentItem = this.mCurrentQuestionIndex

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_eye_protection_mode -> {
                toggleToolBarButton(iv_eye_protection_mode, mToolBarButtonSelectors["eyeProtectionMode"]!!)
                mQuestionRecordList.forEach {
                    it.isOpenEyeProtectionMode = !it.isOpenEyeProtectionMode
                }
                EventBus.getDefault().post("toggleEyeProtectionMode")
            }
            R.id.iv_automatic -> {
                val toggle = toggleToolBarButton(v as ImageButton, mToolBarButtonSelectors["automatic"]!!)
                if (toggle) {
                    mAutomaticDisposable = Observable.interval(10, TimeUnit.SECONDS)
                            .observeOn(androidScheduler)
                            .autoDisposable(mScopeProvider)
                            .subscribe {
                                if (vp_exercise.currentItem < mQuestionWrapper.questionList.size - 1)
                                    vp_exercise.currentItem = vp_exercise.currentItem + 1
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

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        this.mCurrentQuestionIndex = position
        invalidateToolBar()
    }

    private fun toggleToolBarButton(view: ImageView, selector: Selector<Int>): Boolean {
        val tag = if (view.tag == "close") "open" else "close"
        val toggle = tag == "open"
        view.tag = tag
        view.setImageResource(selector.get(toggle))
        return toggle
    }

    private fun invalidateToolBar() {
        tv_total_question.text = "${this.mCurrentQuestionIndex + 1}/${mQuestionWrapper.questionCount}"
        tv_right_question.text = mRightQuestionCount.toString()
        tv_wrong_question.text = mWrongQuestionCount.toString()
        tv_last_question.text = if (vp_exercise.currentItem == 0) "无" else "上一题"
        tv_last_question.isClickable = vp_exercise.currentItem != 0
        tv_next_question.text = if (vp_exercise.currentItem == mFragmentList.size - 1) "提交" else "下一题"
        tv_last_question.isClickable = vp_exercise.currentItem != mFragmentList.size - 1

        val currentQuestion = mQuestionWrapper.questionList[vp_exercise.currentItem]
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
        val currentQuestion = mQuestionWrapper.questionList[vp_exercise.currentItem]
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

}
