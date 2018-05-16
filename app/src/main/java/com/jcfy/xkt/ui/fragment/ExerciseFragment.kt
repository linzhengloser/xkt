package com.jcfy.xkt.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseListFragment
import com.jcfy.xkt.module.ExerciseExamination
import com.jcfy.xkt.ui.itemdecoration.MyViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_exercise.*

/**
 * @author linzheng
 */
class ExerciseFragment : BaseListFragment() {

    private val mExerciseList = arrayListOf(
            ExerciseExamination(R.drawable.exercise_random, "随机联系"),
            ExerciseExamination(R.drawable.exercise_chapter_exercises, "章节练习"),
            ExerciseExamination(R.drawable.exercise_special_practice, "专项练习"),
            ExerciseExamination(R.drawable.exercise_untested, "未做题库"),
            ExerciseExamination(R.drawable.exercise_wrong_question_bank, "错题题库"),
            ExerciseExamination(R.drawable.exercise_collection_question_bank, "收藏题库")
    )

    private val mFragmentList: List<Fragment> = listOf(
            ExerciseExaminationFragment.newInstance(mExerciseList),
            ExerciseExaminationFragment.newInstance(mExerciseList)
    )

    override fun loadData() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vp_exercise.adapter = MyViewPagerAdapter(childFragmentManager, mFragmentList)
    }

    companion object {
        fun newInstance(): ExerciseFragment {
            val args = Bundle()
            val fragment = ExerciseFragment()
            fragment.arguments = args
            return fragment
        }
    }

}