package com.jcfy.xkt.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseListFragment
import com.jcfy.xkt.dp
import com.jcfy.xkt.module.Exercise
import com.jcfy.xkt.ui.multitype.ExerciseItemViewBinder
import kotlinx.android.synthetic.main.fragment_exercise.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class ExerciseFragment : BaseListFragment() {

    private val mExerciseList = listOf(
            Exercise(R.drawable.exercise_random, "随机联系"),
            Exercise(R.drawable.exercise_chapter_exercises, "章节练习"),
            Exercise(R.drawable.exercise_special_practice, "专项练习"),
            Exercise(R.drawable.exercise_untested, "未做题库"),
            Exercise(R.drawable.exercise_wrong_question_bank, "错题题库"),
            Exercise(R.drawable.exercise_collection_question_bank, "收藏题库")
    )

    override fun loadData() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAdapter.register(Exercise::class, ExerciseItemViewBinder())
        mItems.addAll(mExerciseList)
        srl_exercise.isLoadMoreEnable(false)
        rv_exercise.layoutManager = GridLayoutManager(context, 2)
        rv_exercise.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                val position = parent!!.getChildLayoutPosition(view)
                val padding = 13.dp()
                val paddingHalf = padding.shr(1)
                outRect?.bottom = padding
                outRect?.right = if (position % 2 == 1) padding else paddingHalf
                outRect?.left = if (position % 2 == 1) paddingHalf else padding

            }
        })
        rv_exercise.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
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