package com.jcfy.xkt.module.mine

/**
 * @author linzheng
 */


data class RechargeCenterWrapper(
        val rechargeB: List<RechargeCenter>,
        val rechargeA: List<RechargeCenter>
)

data class RechargeCenter(
        val duration: Int,
        val grade: Int,
        val name: String,
        val originalPrice: Int,
        val preferentialPrice: Int,
        val rate: Int,
        val rechargeId: Int,
        val status: Int
)