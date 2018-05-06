package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.Achievement
import com.lz.baselibrary.base.BaseViewHolder
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class AchievementListItemViewBinder: ItemViewBinder<Achievement, AchievementListItemViewBinder.AchievementListViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): AchievementListViewHolder
    = AchievementListViewHolder(inflater.inflate(R.layout.item_achievement,parent,false))
    override fun onBindViewHolder(holder: AchievementListViewHolder, item: Achievement) {
    }


    class AchievementListViewHolder(itemView:View):BaseViewHolder(itemView)

}
