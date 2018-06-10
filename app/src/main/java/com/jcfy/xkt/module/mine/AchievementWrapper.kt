package com.jcfy.xkt.module.mine

/**
 * @author linzheng
 */

data class AchievementWrapper(
        val middleHistory: List<Achievement>,
        val baseHistory: List<Achievement>
)

data class Achievement(
    val createTime: Long,
    val id: Int,
    val rank: Int,
    val score: String,
    val status: Int,
    val type: Int,
    val updateTime: Long,
    val userId: Int
)