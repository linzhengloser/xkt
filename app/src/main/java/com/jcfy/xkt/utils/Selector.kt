package com.jcfy.xkt.utils

/**
 * @author linzheng
 */
data class Selector<T>(val normal: T, private val press: T){

    fun get(toggle:Boolean)= if (toggle) press else normal

}
