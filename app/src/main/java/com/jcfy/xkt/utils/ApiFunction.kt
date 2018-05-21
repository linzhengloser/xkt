package com.jcfy.xkt.utils

import com.jcfy.xkt.api.ApiException
import com.jcfy.xkt.api.BaseData
import io.reactivex.functions.Function

/**
 * @author linzheng
 */
class ApiFunction<T> : Function<BaseData<T>, T> {
    override fun apply(t: BaseData<T>): T {
        return if (!t.isStatus) {
            throw ApiException(t.errorCode,t.errorMsg)
        } else {
            t.data
        }
    }
}