package com.jcfy.xkt.api

/**
 * @author linzheng
 */
data class Response<T>(val status: Boolean, val errorCode: String, val errorMsg: String, val data: T)
