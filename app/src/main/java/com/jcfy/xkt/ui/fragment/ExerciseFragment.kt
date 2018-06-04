package com.jcfy.xkt.ui.fragment

import android.os.Bundle
import android.view.View
import com.jcfy.xkt.api.ExerciseApi
import com.jcfy.xkt.module.ExerciseExaminationMenu
import com.jcfy.xkt.ui.activity.ChapterExerciseActivity
import com.lz.baselibrary.network.Api
import org.jetbrains.anko.startActivity

/**
 * @author linzheng
 */

class ExerciseFragment : ExerciseExaminationFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mType = 1
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onItemClickEvent(event: ExerciseExaminationMenu) {
        if (event.title == CHAPTER_EXERCISE) {
            //章节
            context?.startActivity<ChapterExerciseActivity>("type" to mLevel)
            return
        } else if (event.title == SPECIAL_EXERCISE) {
            //专项练习
            mSpecialExerciseDialog.show()
            return
        }
        val api = Api.createApi(ExerciseApi::class)
        val observable = when (event.title) {
            RANDOM_EXERCISE -> api.getRandomQuestionList(mLevel)
            NO_DONE_EXERCISE -> api.getNotDoneQuestionList(mLevel)
            WRONG_EXERCISE -> api.getWrongQuestionList(mLevel)
            COLLECTION_EXERCISE -> api.getCollectionQuestionList(mLevel)
            else -> null
        }
        handleQuestionList(observable!!, event.title)
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