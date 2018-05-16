package com.jcfy.xkt.module

/**
 * @author linzheng
 */

data class UserWrapper(
    val user: User
)

data class User(
    val province: String,
    val city: String,
    val sex: String,
    val mobile: String,
    val nikeName: String,
    val userType: Int,
    val userName: String,
    val userIcon: String,
    val experience: String,
    val userId: Int,
    val userNumber: String,
    val birthDate: String
)