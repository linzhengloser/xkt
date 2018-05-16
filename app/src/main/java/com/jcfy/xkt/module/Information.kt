package com.jcfy.xkt.module

/**
 * @author linzheng
 */
data class InformationWrapper(
        val newsList: List<Information>,
        val pageNo: Int,
        val totalPage: Int,
        val pageSize: Int,
        val isUpdated: Boolean,
        val totalRecord: Int,
        val dictList: List<Dict>,
        val timestamp: String
)

data class Information(
        val imgUrl: String,
        val newsId: Int,
        val publishDate: Long,
        val subhead: String,
        val clickNum: Int,
        val type: Int,
        val title: String
)

data class Dict(
        val dataName: String,
        val dictId: Int
)