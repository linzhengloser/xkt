package com.jcfy.xkt.module

import com.squareup.moshi.Moshi

/**
 * @author linzheng
 */

data class UserWrapper(
        val user: User
)

data class User(
        var province: String,
        var city: String,
        var sex: String,
        var mobile: String,
        var nikeName: String,
        var userType: Int,
        var userName: String,
        var userIcon: String,
        var experience: String,
        var userId: Int,
        var userNumber: String,
        var birthDate: String,
        var token: String
) {

    override fun toString(): String {
        return Moshi.Builder().build().adapter(User::class.java).toJson(this)
    }

}