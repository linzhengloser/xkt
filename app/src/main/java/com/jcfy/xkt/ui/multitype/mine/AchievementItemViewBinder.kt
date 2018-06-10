package com.jcfy.xkt.ui.multitype.mine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.bindAchievement
import com.jcfy.xkt.module.mine.Achievement
import com.jcfy.xkt.ui.fragment.TYPE_PRIMARY
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_achievement.view.*
import me.drakeet.multitype.ItemViewBinder
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author linzheng
 */
class AchievementItemViewBinder(val type:Int): ItemViewBinder<Achievement, AchievementItemViewBinder.AchievementListViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): AchievementListViewHolder
    = AchievementListViewHolder(inflater.inflate(R.layout.item_achievement, parent, false))
    override fun onBindViewHolder(holder: AchievementListViewHolder, item: Achievement) {
        holder.itemView.apply {
            tv_number.text = item.id.toString()
            tv_level.text = if(type == TYPE_PRIMARY) "初级" else "中级"
            tv_type.text = if(item.status == 0) "理论" else "未知类型"
            tv_date.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(item.updateTime))
            tv_achievement.bindAchievement(item.score)
        }
    }


    class AchievementListViewHolder(itemView:View):BaseViewHolder(itemView)

}
