package com.jcfy.xkt.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseListFragment
import com.jcfy.xkt.module.ExerciseExamination
import com.jcfy.xkt.ui.itemdecoration.MyViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_examination.*

/**
 * @author linzheng
 */
class ExaminationFragment : BaseListFragment() {

    private val mExaminationList = arrayListOf(
            ExerciseExamination(R.drawable.examination_mock, "模拟考试"),
            ExerciseExamination(R.drawable.examination_wrong_question_strengthen, "错题强化"),
            ExerciseExamination(R.drawable.examination_special_item_strengthen, "专项强化"),
            ExerciseExamination(R.drawable.examination_record, "考试记录")
    )

    private val mFragmentList = listOf(
            ExerciseExaminationFragment.newInstance(mExaminationList),
            ExerciseExaminationFragment.newInstance(mExaminationList)
    )

    override fun loadData() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_examination, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vp_examination.adapter = MyViewPagerAdapter(childFragmentManager, mFragmentList)
    }

    companion object {
        fun newInstance(): ExaminationFragment {
            val args = Bundle()
            val fragment = ExaminationFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
