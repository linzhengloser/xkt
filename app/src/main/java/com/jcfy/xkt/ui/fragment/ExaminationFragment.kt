package com.jcfy.xkt.ui.fragment

import android.os.Bundle
import android.view.View
import com.jcfy.xkt.api.ExaminationApi
import com.jcfy.xkt.module.ExerciseExaminationMenu
import com.jcfy.xkt.ui.activity.LoginActivity
import com.jcfy.xkt.utils.UserUtils
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.utils.ToastUtils
import org.jetbrains.anko.support.v4.startActivity

/**
 * @author linzheng
 */


class ExaminationFragment : ExerciseExaminationFragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mType = 2
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onItemClickEvent(event: ExerciseExaminationMenu) {
        if (!UserUtils.isLogin) {
            ToastUtils.showToast("请先登录")
            startActivity<LoginActivity>()
            return
        }
        if (event.title == EXAMINATION_RECORD) {
            return
        }
        val api = Api.createApi(ExaminationApi::class)
        val observable = when (event.title) {
            MOCK_EXAMINATION -> api.getMockQuestionList(mLevel)
            WRONG_QUESTION_STRENGTHEN -> api.getWrongQuestionStrengthenList(mLevel)
            else -> null
        }
        // 合格方法会被多次调用
        if (observable != null)
            handleQuestionList(observable, event.title)
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
