package com.jcfy.xkt.ui.fragment

import android.os.Bundle
import android.view.View
import com.jcfy.xkt.api.ExaminationApi
import com.jcfy.xkt.module.ExerciseExaminationMenu
import com.lz.baselibrary.network.Api

/**
 * @author linzheng
 */


class ExaminationFragment : ExerciseExaminationFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mType = 2
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onItemClickEvent(event: ExerciseExaminationMenu) {
        if (event.title == EXAMINATION_RECORD) {
            return
        }
        val api = Api.createApi(ExaminationApi::class)
        val observable = when (event.title) {
            MOCK_EXAMINATION -> api.getMockQuestionList(mLevel)
            WRONG_QUESTION_STRENGTHEN -> api.getWrongQuestionStrengthenList(mLevel)
            else -> null
        }
        handleQuestionList(observable!!, event.title)
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
