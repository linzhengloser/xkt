package com.jcfy.xkt.ui.activity

import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseActivity
import com.jcfy.xkt.module.question.QuestionRecord
import com.jcfy.xkt.module.question.QuestionWrapper
import com.jcfy.xkt.ui.fragment.QuestionFragment
import com.jcfy.xkt.utils.Selector
import kotlinx.android.synthetic.main.activity_exercise.*
import org.greenrobot.eventbus.EventBus

/**
 * @author linzheng
 */
open abstract class QuestionActivity : BaseActivity(), View.OnClickListener, ViewPager.OnPageChangeListener {

    protected val mToolBarButtonSelectors = mapOf(
            "collection" to Selector(R.drawable.exercise_collection_normal, R.drawable.exercise_collection_press),
            "eyeProtectionMode" to Selector(R.drawable.exercise_eye_protection_mode_normal, R.drawable.exercise_eye_protection_mode_press),
            "automatic" to Selector(R.drawable.exercise_automatic_normal, R.drawable.exercise_automatic_press),
            "showAnswer" to Selector(R.drawable.exercise_question_not_view_answer, R.drawable.exercise_question_view_answer)
    )

    protected val mFragmentList = mutableListOf<QuestionFragment>()

    /**
     * 数据对象
     */
    protected lateinit var mQuestionWrapper: QuestionWrapper

    /**
     * 答题记录
     */
    protected var mQuestionRecordList = mutableListOf<QuestionRecord>()

    /**
     * 否显示答案
     */
    protected var mIsShowAnswer = false

    /**
     * 当前题目索引
     */
    protected var mCurrentQuestionIndex = 0

    /**
     * 答对的题目数量
     */
    protected var mRightQuestionCount: Int = 0

    /**
     * 答错的题目数量
     */
    protected var mWrongQuestionCount: Int = 0

    protected open val mContentViewLayoutId = 0

    // 1 练习 2 考试
    protected abstract val mType:Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mContentViewLayoutId)
        mQuestionWrapper = intent.getParcelableExtra("questionWrapper")
        this.mCurrentQuestionIndex = intent.getIntExtra("index", 0)
        mQuestionWrapper.questionList.forEach {
            val questionRecord = QuestionRecord(it.questionId)
            questionRecord.isShowAnswer = mIsShowAnswer
            mQuestionRecordList.add(questionRecord)
            mFragmentList.add(QuestionFragment.newInstance(it, questionRecord))
        }
        vp_menu.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = mFragmentList[position]
            override fun getCount() = mFragmentList.size
        }
        initListener()
        vp_menu.currentItem = this.mCurrentQuestionIndex
        if (mCurrentQuestionIndex == 0)
            invalidateToolBar()
    }

    private fun initListener() {
        // 上一题
        tv_last_question.setOnClickListener {
            vp_menu.currentItem = vp_menu.currentItem - 1
        }
        // 下一题
        tv_next_question.setOnClickListener {
            vp_menu.currentItem = vp_menu.currentItem + 1
        }
        //护眼模式
        iv_eye_protection_mode.setOnClickListener(this)
        sb_question_progress.max = mQuestionWrapper.questionCount
        vp_menu.addOnPageChangeListener(this)
    }

    protected open fun invalidateToolBar() {
        tv_total_question.text = "${this.mCurrentQuestionIndex + 1}/${mQuestionWrapper.questionCount}"
        sb_question_progress.progress = mCurrentQuestionIndex + 1
        calculateQuestionCount()
    }


    private fun toggleToolBarButton(view: ImageView, selector: Selector<Int>): Boolean {
        val tag = if (view.tag == "close") "open" else "close"
        val toggle = tag == "open"
        view.tag = tag
        view.setImageResource(selector.get(toggle))
        return toggle
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        mCurrentQuestionIndex = position
        invalidateToolBar()
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_eye_protection_mode) {
            toggleToolBarButton(iv_eye_protection_mode, mToolBarButtonSelectors["eyeProtectionMode"]!!)
            mQuestionRecordList.forEach {
                it.isOpenEyeProtectionMode = !it.isOpenEyeProtectionMode
            }
            EventBus.getDefault().post("toggleEyeProtectionMode")
        }
    }

    /**
     *  验证是否答题
     */
    protected fun validateQuestion(): Boolean {
        return mRightQuestionCount == 0 && mWrongQuestionCount == 0
    }

    /**
     * 计算题目对/错的数量
     */
    private fun calculateQuestionCount() {
        if (mCurrentQuestionIndex == 0)
            return
        val currentQuestionRecord = mQuestionRecordList[mCurrentQuestionIndex - 1]
        val currentQuestion = mQuestionWrapper.questionList[mCurrentQuestionIndex - 1]
        when {
            currentQuestionRecord.selectionIndexArray.isEmpty() -> currentQuestionRecord.status = 2
            calculateAnswerIsRight(currentQuestion.answerlist, currentQuestionRecord) -> currentQuestionRecord.status = 1
            else -> currentQuestionRecord.status = 2
        }
        mRightQuestionCount = mQuestionRecordList.count { it.status == 1 }
        mWrongQuestionCount = mQuestionRecordList.count { it.status == 2 }
    }

    /**
     * 计算答案是否是对的
     */
    private fun calculateAnswerIsRight(answerList: List<String>, questionRecord: QuestionRecord): Boolean {
        questionRecord.selectionIndexArray.forEach {
            val selectionAnswer = it.toString()
            if (answerList.none { it == selectionAnswer }) {
                return false
            }
        }
        return true
    }

}
