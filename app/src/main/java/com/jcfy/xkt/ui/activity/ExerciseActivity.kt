package com.jcfy.xkt.ui.activity

import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseActivity
import com.jcfy.xkt.module.question.QuestionRecord
import com.jcfy.xkt.module.question.QuestionWrapper
import com.jcfy.xkt.setMenuImage
import com.jcfy.xkt.setTitleText
import com.jcfy.xkt.ui.fragment.QuestionFragment
import kotlinx.android.synthetic.main.activity_exercise.*

/**
 * @author linzheng
 */

class ExerciseActivity : BaseActivity(), ViewPager.OnPageChangeListener {

    val mFragmentList = mutableListOf<QuestionFragment>()

    private lateinit var mQuestionWrapper: QuestionWrapper

    private var mQuestionRecordList = mutableListOf<QuestionRecord>()

    private var mAlreadyDoQuestionCount: Int = 0

    private var mRightQuestionCount: Int = 0

    private var mWrongQuestionCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        mQuestionWrapper = intent.getParcelableExtra("questionWrapper")
        setTitleText("练习")
        setMenuImage(R.drawable.exercise_eye_protection_close)

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

        invalidateToolBar()

        tv_last_question.setOnClickListener {
            vp_exercise.currentItem = vp_exercise.currentItem - 1
        }
        tv_next_question.setOnClickListener {
            vp_exercise.currentItem = vp_exercise.currentItem + 1
        }

    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        mAlreadyDoQuestionCount = position + 1
        invalidateToolBar()
    }

    private fun invalidateToolBar() {
        tv_total_question.text = "$mAlreadyDoQuestionCount/${mQuestionWrapper.questionCount}"
        tv_right_question.text = mRightQuestionCount.toString()
        tv_wrong_question.text = mWrongQuestionCount.toString()
        tv_last_question.text = if (vp_exercise.currentItem == 0) "无" else "上一题"
        tv_last_question.isClickable = vp_exercise.currentItem != 0
        tv_next_question.text = if (vp_exercise.currentItem == mFragmentList.size - 1) "提交" else "下一题"
        tv_last_question.isClickable = vp_exercise.currentItem != mFragmentList.size - 1
    }

}
