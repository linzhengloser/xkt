package com.jcfy.xkt.api

/**
 * @author linzheng
 */


class ApiException(var errorCode: String = "", var errorMessage: String = "") : Exception() {
    override fun toString(): String {
        return "Api Exception errorCode = $errorCode errorMessage = $errorMessage!"
    }
}
