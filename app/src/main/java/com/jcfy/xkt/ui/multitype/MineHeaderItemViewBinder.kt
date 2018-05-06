package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.User
import com.lz.baselibrary.base.BaseViewHolder
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class MineHeaderItemViewBinder : ItemViewBinder<User, MineHeaderItemViewBinder.MineHeaderViewHolder>() {
    override fun onBindViewHolder(holder: MineHeaderViewHolder, item: User) {
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): MineHeaderViewHolder
    = MineHeaderViewHolder(inflater.inflate(R.layout.item_mine_header,parent,false))


    class MineHeaderViewHolder(itemView:View) : BaseViewHolder(itemView){}

}
