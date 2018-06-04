package com.jcfy.xkt.module.mine

/**
 * @author linzheng
 */

data class HelpWrapper(
    val help: Help
)

data class Help(
    val content: String,
    val createTime: Long,
    val creater: String,
    val helpId: Int,
    val helpType: Int,
    val lastTime: Long,
    val laster: String,
    val sort: Int,
    val status: Int,
    val title: String,
    val typeName: String
)
