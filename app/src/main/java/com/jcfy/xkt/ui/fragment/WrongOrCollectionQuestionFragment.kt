package com.jcfy.xkt.ui.fragment

import android.os.Bundle
import android.view.View
import com.jcfy.xkt.module.Chapter
import com.jcfy.xkt.module.mine.Schedule
import com.jcfy.xkt.ui.multitype.mine.QuestionChildItemViewBinder
import com.jcfy.xkt.ui.multitype.mine.QuestionParentItemViewBinder
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class WrongOrCollectionQuestionFragment : PrimaryOrIntermediateQuestionScheduleFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter.apply {
            register(Schedule::class, QuestionParentItemViewBinder(this@WrongOrCollectionQuestionFragment))
            register(Chapter::class, QuestionChildItemViewBinder())
        }
    }

    companion object {
        fun newInstance(type: Int): WrongOrCollectionQuestionFragment {
            val args = Bundle()
            args.putInt("type", type)
            val fragment = WrongOrCollectionQuestionFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
