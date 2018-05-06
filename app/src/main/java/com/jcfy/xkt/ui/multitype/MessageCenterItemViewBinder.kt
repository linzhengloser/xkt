package com.jcfy.xkt.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.Message
import com.lz.baselibrary.base.BaseViewHolder
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class MessageCenterItemViewBinder : ItemViewBinder<Message, MessageCenterItemViewBinder.MessageCenterViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    = MessageCenterViewHolder(inflater.inflate(R.layout.item_message_center,parent,false))

    override fun onBindViewHolder(holder: MessageCenterViewHolder, item: Message) {
    }

    class MessageCenterViewHolder(itemView: View) : BaseViewHolder(itemView)

}
