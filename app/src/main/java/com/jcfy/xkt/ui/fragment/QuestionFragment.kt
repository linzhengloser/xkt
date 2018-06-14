package com.jcfy.xkt.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jcfy.xkt.*
import com.jcfy.xkt.api.QUESTION_TYPE_MULTI
import com.jcfy.xkt.base.BaseFragment
import com.jcfy.xkt.module.question.Question
import com.jcfy.xkt.module.question.QuestionRecord
import com.jcfy.xkt.utils.selection.QuestionOptionTextViewSelectionBinder
import com.jcfy.xkt.utils.selection.SelectionAdapter
import kotlinx.android.synthetic.main.fragment_question.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.backgroundColor

/**
 * @author linzheng
 */

class QuestionFragment : BaseFragment() {

    private lateinit var mQuestion: Question

    private lateinit var mQuestionRecord: QuestionRecord

    private val mSelectionAdapter = SelectionAdapter().apply {
        register(TextView::class.java, QuestionOptionTextViewSelectionBinder())
    }

    override fun loadData() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mQuestion = arguments?.getParcelable("question")!!
        mQuestionRecord = arguments?.getParcelable("questionRecord")!!
        setData()
        setListener()
    }

    private fun setListener() {
        mSelectionAdapter.setListener { index, toggle ->
            saveQuestionRecord(index, toggle)
        }
    }

    private fun saveQuestionRecord(index: Int, toggle: Boolean) {
        if (mQuestion.type != QUESTION_TYPE_MULTI) mQuestionRecord.selectionIndexArray.clear()
        if (toggle) {
            mQuestionRecord.selectionIndexArray.add(index)
        } else {
            mQuestionRecord.selectionIndexArray.remove(index)
        }
    }

    private fun setData() {
        tv_content.text = mQuestion.content
        tv_type.bindQuestionType(mQuestion.type)
        val successAnswer = StringBuilder("正确答案")
        mQuestion.answerlist.forEach { successAnswer.append(answerOptionsString[it.toInt()]) }
        tv_success_answer.text = successAnswer
        tv_answer_resolve.text = "答案解析：$mQuestion.explanation"
        ll_options.bindQuestionOptions(mQuestion.optionsList)
        mSelectionAdapter.bindLayout(ll_options)
        if (mQuestion.type != QUESTION_TYPE_MULTI)
            mSelectionAdapter.singleSelection()
        restoreData()
    }

    /**
     * 根据 mQuestionRecord 恢复数据
     */
    private fun restoreData() {
        mQuestionRecord.selectionIndexArray.forEach {
            mSelectionAdapter.setSelection(it)
        }
        toggleEyeProtectionMode()
        toggleShowAnswer()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    public fun onToolBarUpdatedEvent(event: String) {
        when (event) {
            "toggleEyeProtectionMode" -> toggleEyeProtectionMode()
            "toggleShowAnswer" -> toggleShowAnswer()
        }
    }

    private fun toggleShowAnswer() {
        tv_view_answer.bindBoolean2Visibility(mQuestionRecord.isShowAnswer)
        tv_success_answer.bindBoolean2Visibility(mQuestionRecord.isShowAnswer)
        tv_answer_resolve.bindBoolean2Visibility(mQuestionRecord.isShowAnswer)
    }

    private fun toggleEyeProtectionMode() {
        val backgroundColor = if (mQuestionRecord.isOpenEyeProtectionMode) Color.parseColor("#c7edcc") else Color.WHITE
        sv_root.backgroundColor = backgroundColor
    }

    companion object {

        fun newInstance(question: Question, questionRecord: QuestionRecord): QuestionFragment {
            val args = Bundle()
            val fragment = QuestionFragment()
            args.putParcelable("question", question)
            args.putParcelable("questionRecord", questionRecord)
            fragment.arguments = args
            return fragment
        }
    }
}
