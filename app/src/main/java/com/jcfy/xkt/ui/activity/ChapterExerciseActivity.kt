package com.jcfy.xkt.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jcfy.xkt.*
import com.jcfy.xkt.api.ExerciseApi
import com.jcfy.xkt.base.BaseListActivity
import com.jcfy.xkt.base.LoadListData
import com.jcfy.xkt.module.Chapter
import com.jcfy.xkt.module.Writings
import com.jcfy.xkt.ui.multitype.ChapterChildItemViewBinder
import com.jcfy.xkt.ui.multitype.ChapterParentItemViewBinder
import com.lz.baselibrary.model.expandlist.Child
import com.lz.baselibrary.model.expandlist.Parent
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.utils.OnParentClickListener
import com.lz.baselibrary.view.RefreshListener
import com.lz.baselibrary.view.VerticalItemDecoration
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_chapter_exercise.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class ChapterExerciseActivity : BaseListActivity(), OnParentClickListener<Writings>, RefreshListener, LoadListData {

    private val mExpandItems: MutableMap<Parent, List<Child>> by lazy(LazyThreadSafetyMode.NONE) {
        HashMap<Parent, List<Child>>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_exercise)
        setTitleText("章节练习")
        srl_chapter_exercise.isLoadMoreEnable(false)
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
        Utils.handleListData(api.getChapterList(), isInitialize, srl_chapter_exercise, this, mScopeProvider, Consumer {
            showSuccessLayout()
            it.writingsList.forEach {
                mItems.add(it)
                mItems.addAll(it.chapterList)
            }
            mAdapter.notifyDataSetChanged()
        })
    }

    override fun refresh(isRefresh: Boolean) {
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

}