package com.jcfy.xkt.module

/**
 * @author linzheng
 */

data class ColumnWrapper(
    val pageNo: Int,
    val totalPage: Int,
    val columnList: List<Column>,
    val pageSize: Int,
    val isUpdated: Boolean,
    val totalRecord: Int,
    val timestamp: String
)

data class Column(
    val imgUrl: String,
    val columnId: Int,
    val columnDesc: String,
    val createDate: String
)