package com.jcfy.xkt.utils

import com.jcfy.xkt.api.ApiException
import com.jcfy.xkt.api.Response
import io.reactivex.functions.Function

/**
 * @author linzheng
 */
class ApiFunction<T> : Function<Response<T>, T> {
    override fun apply(t: Response<T>): T {
        return if (!t.status) {
            throw ApiException(t.errorCode, t.errorMsg)
        } else {
            t.data
        }
    }
}