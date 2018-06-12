package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.ui.activity.LoginActivity
import com.jcfy.xkt.ui.activity.mine.AchievementActivity
import com.jcfy.xkt.ui.activity.mine.ScheduleActivity
import com.jcfy.xkt.ui.activity.mine.WrongOrCollectionQuestionActivity
import com.jcfy.xkt.utils.UserUtils
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_mine_header.view.*
import me.drakeet.multitype.ItemViewBinder
import org.jetbrains.anko.startActivity

/**
 * @author linzheng
 */
class MineHeaderItemViewBinder : ItemViewBinder<String, MineHeaderItemViewBinder.MineHeaderViewHolder>() {
    override fun onBindViewHolder(holder: MineHeaderViewHolder, item: String) {
        holder.itemView.apply {
            tv_nickname.text = if (UserUtils.isLogin) UserUtils.user?.nikeName else "请先登录"
            setOnClickListener {
                if (!UserUtils.isLogin)
                    context.startActivity<LoginActivity>()
            }
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): MineHeaderViewHolder = MineHeaderViewHolder(inflater.inflate(R.layout.item_mine_header, parent, false))

    class MineHeaderViewHolder(itemView: View) : BaseViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.fl_schedule -> v.context.startActivity<ScheduleActivity>()
                R.id.fl_wrong_question -> v.context.startActivity<WrongOrCollectionQuestionActivity>("type" to 1)
                R.id.fl_collection -> v.context.startActivity<WrongOrCollectionQuestionActivity>("type" to 2)
                R.id.fl_achievement -> v.context.startActivity<AchievementActivity>()
            }
        }

        init {
            itemView.apply {
                fl_achievement.setOnClickListener(this@MineHeaderViewHolder)
                fl_schedule.setOnClickListener(this@MineHeaderViewHolder)
                fl_wrong_question.setOnClickListener(this@MineHeaderViewHolder)
                fl_collection.setOnClickListener(this@MineHeaderViewHolder)
            }
        }
    }

}
