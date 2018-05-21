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
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

/**
 * @author linzheng
 */
class MineHeaderItemViewBinder : ItemViewBinder<String, MineHeaderItemViewBinder.MineHeaderViewHolder>() {
    override fun onBindViewHolder(holder: MineHeaderViewHolder, item: String) {
        holder.itemView.tv_nickname.text = if (UserUtils.isLogin) UserUtils.user?.nikeName else "请先登录"
        holder.itemView.ll_avatar_nickname.onClick {

            if(!UserUtils.isLogin)
                holder.itemView.context.startActivity<LoginActivity>()

        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): MineHeaderViewHolder = MineHeaderViewHolder(inflater.inflate(R.layout.item_mine_header, parent, false))


    class MineHeaderViewHolder(itemView: View) : BaseViewHolder(itemView)

}
