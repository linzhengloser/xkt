package com.jcfy.xkt.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseListActivity
import com.jcfy.xkt.dp
import com.jcfy.xkt.module.Achievement
import com.jcfy.xkt.setTitleText
import com.jcfy.xkt.ui.multitype.AchievementListItemViewBinder
import com.lz.baselibrary.view.VerticalItemDecoration
import kotlinx.android.synthetic.main.activity_achievement_list.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class AchievementListActivity : BaseListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievement_list)

        setTitleText("成绩")
        mAdapter.register(Achievement::class, AchievementListItemViewBinder())
        mItems.addAll(listOf(Achievement(), Achievement(), Achievement(), Achievement(), Achievement()))

        rv_achievement_list.addItemDecoration(VerticalItemDecoration(1.dp(), resources.getColor(R.color.itemDividerColor)))
        rv_achievement_list.layoutManager = LinearLayoutManager(this)
        rv_achievement_list.adapter = mAdapter

        mAdapter.notifyDataSetChanged()

    }

}
