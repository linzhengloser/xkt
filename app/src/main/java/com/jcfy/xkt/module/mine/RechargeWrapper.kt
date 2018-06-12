package com.jcfy.xkt.module.mine

/**
 * @author linzheng
 */

data class RechargeWrapper(
        val rechargeB: List<Recharge>,
        val rechargeA: List<Recharge>
)

data class Recharge(
        val duration: Int,
        val grade: Int,
        val name: String,
        val originalPrice: Int,
        val preferentialPrice: Int,
        val rate: Int,
        val rechargeId: Int,
        val status: Int,
        val isOddNumber: Boolean = true
)
