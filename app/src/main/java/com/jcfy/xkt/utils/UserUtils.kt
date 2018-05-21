package com.jcfy.xkt.utils

import android.text.TextUtils
import androidx.core.content.edit
import com.jcfy.xkt.module.User
import com.lz.baselibrary.LibraryApplication
import com.squareup.moshi.Moshi
import org.jetbrains.anko.defaultSharedPreferences

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/02/05
 * desc   : 用户管理类
 * version: 1.0
</pre> *
 */
object UserUtils {

    private var sUser: User? = null

    val user: User?
        get() {
            if (sUser == null) {
                val userJson = LibraryApplication.getInstance().defaultSharedPreferences.getString("user_json", "")
                if (!TextUtils.isEmpty(userJson))
                    sUser = Moshi.Builder().build().adapter(User::class.java).fromJson(userJson)
            }
            return sUser
        }

    val isLogin: Boolean
        get() = user != null

    fun updateNickName(nickName: String) {
        sUser!!.nikeName = nickName
        LibraryApplication.getInstance().defaultSharedPreferences.edit {
            putString("user_json", sUser?.toString())
        }
    }

    fun updatePhoneNumber(phoneNumber: String) {
        sUser!!.mobile = phoneNumber
        LibraryApplication.getInstance().defaultSharedPreferences.edit {
            putString("user_json", sUser?.toString())
        }
    }

    fun clearUser() {
        LibraryApplication.getInstance().defaultSharedPreferences.edit {
            putString("user_json", "")
        }
        sUser = null
    }

    fun saveUser(user: User) {
        LibraryApplication.getInstance().defaultSharedPreferences.edit {
            val userJson = user.toString()
            putString("user_json", userJson)
        }
        sUser = user
    }

}
