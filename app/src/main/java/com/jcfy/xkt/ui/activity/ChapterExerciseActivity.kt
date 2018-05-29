package com.jcfy.xkt.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jcfy.xkt.*
import com.jcfy.xkt.api.ApiConsumer
import com.jcfy.xkt.api.ExerciseApi
import com.jcfy.xkt.base.BaseListActivity
import com.jcfy.xkt.base.LoadListData
import com.jcfy.xkt.module.Chapter
import com.jcfy.xkt.module.Writings
import com.jcfy.xkt.ui.multitype.ChapterChildItemViewBinder
import com.jcfy.xkt.ui.multitype.ChapterParentItemViewBinder
import com.jcfy.xkt.utils.ApiFunction
import com.lz.baselibrary.model.expandlist.Child
import com.lz.baselibrary.model.expandlist.Parent
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.utils.OnParentClickListener
import com.lz.baselibrary.utils.ToastUtils
import com.lz.baselibrary.view.RefreshListener
import com.lz.baselibrary.view.VerticalItemDecoration
import com.uber.autodispose.kotlin.autoDisposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_chapter_exercise.*
import me.drakeet.multitype.register
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.startActivity

/**
 * @author linzheng
 */
class ChapterExerciseActivity : BaseListActivity(), OnParentClickListener<Writings>, RefreshListener, LoadListData {

    private val mExpandItems: MutableMap<Parent, List<Child>> by lazy(LazyThreadSafetyMode.NONE) {
        HashMap<Parent, List<Child>>()
    }

    private var mType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_exercise)
        mType = intent.getIntExtra("type",0)
        setTitleText("章节练习")
        srl_chapter_exercise.setRefreshListener(this)
        mAdapter.register(Writings::class, ChapterParentItemViewBinder(this))
        mAdapter.register(Chapter::class, ChapterChildItemViewBinder())
        rv_chapter_exercise.apply {
            addItemDecoration(VerticalItemDecoration(1.dp(), getResourceColor(R.color.itemDividerColor)))
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        loadListData()
    }

    override fun loadListData(isInitialize: Boolean, isRefresh: Boolean) {
        val api = Api.createApi(ExerciseApi::class)
        Utils.handleListData(api.getChapterList(mType), isInitialize, srl_chapter_exercise, this, mScopeProvider, Consumer {
            showSuccessLayout()
            srl_chapter_exercise.isLoadMoreEnable(false)
            it.writingsList.forEach {
                mItems.add(it)
                mItems.addAll(it.chapterList)
                it.isExpend = true
                mExpandItems[it] = it.chapterList
            }
            mAdapter.notifyDataSetChanged()
        })
    }

    override fun refresh(isRefresh: Boolean) {
        mItems.clear()
        loadListData(false, true)
    }

    override fun onParentClick(v: View, parent: Writings) {
        var parentIndex = mItems.indexOf(parent)
        if (parentIndex == -1) return
        if (parent.isExpend) {
            mExpandItems[parent]?.forEach { mItems.remove(it) }
        } else {
            mExpandItems[parent]?.let { mItems.addAll(parentIndex + 1, it) }
            mAdapter.notifyItemInserted(parentIndex)
        }
        parent.isExpend = !parent.isExpend
        mAdapter.notifyDataSetChanged()
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
    public fun onChilClickListener(chapter: Chapter) {
        val api = Api.createApi(ExerciseApi::class)
        api.getQuestionListByChapterId(chapter.chapterId)
                .doOnSubscribe { showLoadingDialog() }
                .doFinally { hideLoadingDialog() }
                .observeOn(androidScheduler)
                .map(ApiFunction())
                .autoDisposable(mScopeProvider)
                .subscribe(Consumer {
                    if (it.questionList.isEmpty()) {
                        ToastUtils.showToast("此章节下暂无题目")
                        return@Consumer
                    }
                    startActivity<ExerciseActivity>("questionWrapper" to it, "index" to chapter.maxId)
                }, ApiConsumer())
    }

}