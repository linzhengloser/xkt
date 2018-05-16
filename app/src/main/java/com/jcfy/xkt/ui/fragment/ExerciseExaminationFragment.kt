package com.jcfy.xkt.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseListFragment
import com.jcfy.xkt.module.ExerciseExamination
import com.jcfy.xkt.ui.itemdecoration.MyGridItemDecoration
import com.jcfy.xkt.ui.multitype.ExerciseItemViewBinder
import kotlinx.android.synthetic.main.fragment_exercise_examination.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class ExerciseExaminationFragment : BaseListFragment() {

    override fun loadData() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exercise_examination,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter.register(ExerciseExamination::class, ExerciseItemViewBinder())
        mItems.addAll(arguments?.getParcelableArrayList("items")!!)
        rv_exercise_examination.layoutManager = GridLayoutManager(context, 2)
        rv_exercise_examination.addItemDecoration(MyGridItemDecoration())
        rv_exercise_examination.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
    }


    companion object {
        fun newInstance(items:ArrayList<ExerciseExamination>): ExerciseExaminationFragment {
            val args = Bundle()
            args.putParcelableArrayList("items",items)
            val fragment = ExerciseExaminationFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
