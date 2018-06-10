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
import com.jcfy.xkt.api.ExaminationApi
import com.jcfy.xkt.api.ExerciseApi
import com.jcfy.xkt.api.Response
import com.jcfy.xkt.base.BaseListFragment
import com.jcfy.xkt.module.ExerciseExaminationMenu
import com.jcfy.xkt.module.question.QuestionWrapper
import com.jcfy.xkt.ui.activity.ExaminationActivity
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
const val MOCK_EXAMINATION = "模拟考试"

const val WRONG_QUESTION_STRENGTHEN = "错题强化"

const val SPECIAL_STRENGTHEN = "专项强化"

const val EXAMINATION_RECORD = "考试记录"

const val RANDOM_EXERCISE = "随机练习"

const val CHAPTER_EXERCISE = "章节练习"

const val SPECIAL_EXERCISE = "专项练习"

const val NO_DONE_EXERCISE = "未做题库"

const val WRONG_EXERCISE = "错题题库"

const val COLLECTION_EXERCISE = "收藏题库"

open class ExerciseExaminationFragment : BaseListFragment() {

    protected var mLevel = 0

    // 1 练习 2 考试
    protected var mType = 1

    private val mExerciseMenuList = arrayListOf(
            ExerciseExaminationMenu(R.drawable.exercise_random, RANDOM_EXERCISE),
            ExerciseExaminationMenu(R.drawable.exercise_chapter_exercises, CHAPTER_EXERCISE),
            ExerciseExaminationMenu(R.drawable.exercise_special_practice, SPECIAL_EXERCISE),
            ExerciseExaminationMenu(R.drawable.exercise_untested, NO_DONE_EXERCISE),
            ExerciseExaminationMenu(R.drawable.exercise_wrong_question_bank, WRONG_EXERCISE),
            ExerciseExaminationMenu(R.drawable.exercise_collection_question_bank, COLLECTION_EXERCISE)
    )


    private val mExaminationMenuList = arrayListOf(
            ExerciseExaminationMenu(R.drawable.examination_mock, MOCK_EXAMINATION),
            ExerciseExaminationMenu(R.drawable.examination_wrong_question_strengthen, WRONG_QUESTION_STRENGTHEN),
            ExerciseExaminationMenu(R.drawable.examination_special_item_strengthen, SPECIAL_STRENGTHEN),
            ExerciseExaminationMenu(R.drawable.examination_record, EXAMINATION_RECORD)
    )


    private val mSelectionAdapter: SelectionAdapter = SelectionAdapter().apply {
        register(AppCompatTextView::class.java, TabTextViewSectionBinder())
        singleSelection()
        setListener { index, _ ->
            mLevel = index
        }
    }

    protected val mSpecialExerciseDialog: AlertDialog  by lazy {
        AlertDialog.Builder(context!!)
                .setTitle("请选择题目类型")
                .setItems(arrayOf("单选", "多选", "判断"), { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                    val observable = if (mType == 1) {
                        Api.createApi(ExerciseApi::class).getSpecialQuestionList(mLevel, i + 1)
                    } else {
                        Api.createApi(ExaminationApi::class).getSpecialTrengthenQuestionList(mLevel, i + 1)
                    }
                    handleQuestionList(observable, SPECIAL_EXERCISE)
                })
                .create()
    }

    private lateinit var mFragmentList: List<Fragment>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuList = if (mType == 1) {
            mExerciseMenuList
        } else {
            mExaminationMenuList
        }
        mFragmentList = listOf(
                ExerciseExaminationPageFragment.newInstance(menuList),
                ExerciseExaminationPageFragment.newInstance(menuList)
        )
        vp_menu.adapter = MyViewPagerAdapter(childFragmentManager, mFragmentList)
        mSelectionAdapter.bindViewPager(vp_menu, ll_tab)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }


    @Subscribe
    public open fun onItemClickEvent(event: ExerciseExaminationMenu) {

    }

    protected fun handleQuestionList(observable: Observable<Response<QuestionWrapper>>, title: String) {
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
                    if (mType == 1) {
                        context?.startActivity<ExerciseActivity>("questionWrapper" to it)
                    } else {
                        context?.startActivity<ExaminationActivity>("questionWrapper" to it)
                    }
                }, ApiConsumer())
    }

}