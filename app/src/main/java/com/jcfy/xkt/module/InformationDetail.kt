package com.jcfy.xkt.module

/**
 * @author linzheng
 */

data class InformationDetailWrapper(
        val newsDetail: InformationDetail,
        val recommendList: List<Information>
)

data class InformationDetail(
    val date: String,
    val downloadImgUrl: String,
    val videoUrl: String,
    val author: String,
    val origin: String,
    val subhead: String,
    val type: Int,
    val title: String,
    val content: String
)
