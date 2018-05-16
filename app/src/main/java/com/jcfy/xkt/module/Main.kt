package com.jcfy.xkt.module

/**
 * @author linzheng
 */


data class MainWrapper(
        val bannerList: List<Banner>,
        val dictId: Int,
        val resultList: List<Information>
)


data class Banner(
        val imgUrl: String,
        val targetId: String,
        val targetType: Int,
        val pageUrl: String
)