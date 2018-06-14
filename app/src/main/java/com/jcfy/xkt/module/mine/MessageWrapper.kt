package com.jcfy.xkt.module.mine

/**
 * @author linzheng
 */

data class MessageWrapper(
    val msgList: List<Msg>
)

data class Msg(
    val content: String,
    val createTime: Long,
    val managerId: String,
    val messageType: Int,
    val platformType: String,
    val recordId: Int,
    val remark: String,
    val status: Int,
    val targetId: String,
    val title: String,
    val userId: Int
)