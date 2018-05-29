package com.jcfy.xkt.ui.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.androidScheduler
import com.jcfy.xkt.api.ApiConsumer
import com.jcfy.xkt.api.ExerciseApi
import com.jcfy.xkt.api.Response
import com.jcfy.xkt.base.BaseListFragment
import com.jcfy.xkt.module.*
import com.jcfy.xkt.module.question.QuestionWrapper
import com.jcfy.xkt.ui.activity.ChapterExerciseActivity
import com.jcfy.xkt.ui.activity.ExerciseActivity
import com.jcfy.xkt.ui.itemdecoration.MyViewPagerAdapter
import com.jcfy.xkt.utils.ApiFunction
import com.jcfy.xkt.utils.selection.SelectionAdapter
import com.jcfy.xkt.utils.selection.TabTextViewSectionBinder
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.utils.ToastUtils
import com.uber.autodispose.kotlin.autoDisposable
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_exercise.*
import kotlinx.android.synthetic.main.layout_tab.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.startActivity

/**
 * @author linzheng
 */

class ExerciseFragment : BaseListFragment() {

    private val mExerciseList = arrayListOf(
            ExerciseExamination(R.drawable.exercise_random, RANDOM_EXERCISE),
            ExerciseExamination(R.drawable.exercise_chapter_exercises, CHAPTER_EXERCISE),
            ExerciseExamination(R.drawable.exercise_special_practice, SPECIAL_EXERCISE),
            ExerciseExamination(R.drawable.exercise_untested, NO_DONE_EXERCISE),
            ExerciseExamination(R.drawable.exercise_wrong_question_bank, WRONG_EXERCISE),
            ExerciseExamination(R.drawable.exercise_collection_question_bank, COLLECTION_EXERCISE)
    )

    private val mFragmentList: List<Fragment> = listOf(
            ExerciseExaminationFragment.newInstance(mExerciseList),
            ExerciseExaminationFragment.newInstance(mExerciseList)
    )


    private val mSelectionAdapter: SelectionAdapter = SelectionAdapter().apply {
        register(AppCompatTextView::class.java, TabTextViewSectionBinder())
        singleSelection()
        setListener { index, _ ->
            vp_exercise.currentItem = index
        }
    }

    private val mSpecialExerciseDialog: AlertDialog  by lazy {
        AlertDialog.Builder(context!!)
                .setTitle("请选择题目类型")
                .setItems(arrayOf("单选", "多选", "判断"), { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                    val api = Api.createApi(ExerciseApi::class)
                    handleQuestionList(api.getSpecialQuestionList(i + 1, mType), SPECIAL_EXERCISE)
                })
                .create()
    }

    private var mType: Int = 0

    override fun loadData() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vp_exercise.adapter = MyViewPagerAdapter(childFragmentManager, mFragmentList)
        mSelectionAdapter.bindViewPager(vp_exercise, ll_tab)
    }


    companion object {
        fun newInstance(): ExerciseFragment {
            val args = Bundle()
            val fragment = ExerciseFragment()
            fragment.arguments = args
            return fragment
        }
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
    public fun onItemClickEvent(event: ExerciseExamination) {

        if (event.title == CHAPTER_EXERCISE) {
            //章节
            context?.startActivity<ChapterExerciseActivity>("type" to mType)
            return
        } else if (event.title == SPECIAL_EXERCISE) {
            //专项练习
            mSpecialExerciseDialog.show()
            return
        }

        val api = Api.createApi(ExerciseApi::class)
        val observable = when (event.title) {
            RANDOM_EXERCISE -> api.getRandomQuestionList(mType)
            NO_DONE_EXERCISE -> api.getNotDoneQuestionList(mType)
            WRONG_EXERCISE -> api.getWrongQuestionList(mType)
            COLLECTION_EXERCISE -> api.getCollectionQuestionList(mType)
            else -> null
        }
        handleQuestionList(observable!!, event.title)
    }


    private fun handleQuestionList(observable: Observable<Response<QuestionWrapper>>, title: String) {
        observable!!
                .doOnSubscribe { showLoadingDialog() }
                .doFinally { hideLoadingDialog() }
                .observeOn(androidScheduler)
                .map(ApiFunction())
                .autoDisposable(mScopeProvider)
                .subscribe(Consumer {
                    if (it.questionList.isEmpty()) {
                        ToastUtils.showToast("暂无$title")
                        return@Consumer
                    }
                    context?.startActivity<ExerciseActivity>("questionWrapper" to it)
                }, ApiConsumer())
    }


}