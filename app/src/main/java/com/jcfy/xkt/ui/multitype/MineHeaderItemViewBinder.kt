package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.ui.activity.LoginActivity
import com.jcfy.xkt.utils.UserUtils
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_mine_header.view.*
import me.drakeet.multitype.ItemViewBinder
import org.greenrobot.eventbus.EventBus
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
            EventBus.getDefault().post(v?.tag.toString())
        }
        init {
            itemView.fl_schedule.setOnClickListener(this)
        }
    }

}
