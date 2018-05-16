package com.jcfy.xkt.module

/**
 * @author linzheng
 */

data class InformationDetailWrapper(
        val newsDetail: InformationDetail,
        val recommendList: List<Recommend>
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

data class Recommend(
    val imgUrl: String,
    val newsId: Int,
    val publishDate: Long,
    val subhead: String,
    val clickNum: Int,
    val type: Int,
    val title: String
)