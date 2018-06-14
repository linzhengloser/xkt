package com.jcfy.xkt.ui.multitype

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.module.mine.Msg
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_message_center.view.*
import me.drakeet.multitype.ItemViewBinder
import org.jetbrains.anko.backgroundResource
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author linzheng
 */
class MessageCenterItemViewBinder : ItemViewBinder<Msg, MessageCenterItemViewBinder.MessageCenterViewHolder>() {

    private val mMessageTypeBackgroundResIdArray: SparseArray<Int> by lazy {
        val array = SparseArray<Int>(3)
        array.append(0, R.drawable.shape_message_center_system_message_bg)
        array.append(1, R.drawable.shape_message_center_buy_notice_bg)
        array.append(2, R.drawable.shape_message_center_maturity_notice_bg)
        array
    }

    private val mMessageTypeTitleTextArray: SparseArray<String> by lazy {
        val array = SparseArray<String>(3)
        array.append(0,"系统消息")
        array.append(1,"购买通知")
        array.append(2,"到期通知")
        array
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = MessageCenterViewHolder(inflater.inflate(R.layout.item_message_center, parent, false))

    override fun onBindViewHolder(holder: MessageCenterViewHolder, item: Msg) {
        holder.itemView.apply {
            tv_title.text = mMessageTypeTitleTextArray[item.messageType]
            v_icon.backgroundResource = mMessageTypeBackgroundResIdArray[item.messageType]
            tv_date.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(item.createTime))
            tv_content.text = item.content
        }
    }

    class MessageCenterViewHolder(itemView: View) : BaseViewHolder(itemView)

}
