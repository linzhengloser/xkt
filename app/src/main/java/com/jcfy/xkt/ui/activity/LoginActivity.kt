package com.jcfy.xkt.ui.activity

import android.content.Intent
import android.os.Bundle
import com.jcfy.xkt.R
import com.jcfy.xkt.androidScheduler
import com.jcfy.xkt.api.ApiConsumer
import com.jcfy.xkt.api.UserApi
import com.jcfy.xkt.base.BaseActivity
import com.jcfy.xkt.module.UserWrapper
import com.jcfy.xkt.utils.ApiFunction
import com.jcfy.xkt.utils.UserUtils
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.utils.ToastUtils
import com.lz.baselibrary.utils.Utils
import com.uber.autodispose.kotlin.autoDisposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * @author linzheng
 */
class LoginActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTranslucentStatus(true)
        setContentView(R.layout.activity_login)


        tv_register.setOnClickListener {
            startActivity(Intent(it.context, RegisterActivity::class.java))
        }

        tv_login.onClick {
            if (Utils.isEmpty(arrayOf("手机号不能为空!", "密码不能为空"), et_phone, et_pwd))
                login()
        }

    }


    private fun login() {
        val phoneNumber = et_phone.text.trim().toString()
        val pwd = et_pwd.text.trim().toString()

        //TODO 设备ID
        Api.createApi(UserApi::class)
                .login(phoneNumber, pwd, "1")
                .map(ApiFunction())
                .doFinally { hideLoadingDialog() }
                .doOnSubscribe { showLoadingDialog() }
                .observeOn(androidScheduler)
                .autoDisposable(mScopeProvider)
                .subscribe(Consumer<UserWrapper> {
                    UserUtils.saveUser(it.user)
                    ToastUtils.showToast("登录成功！")
                    finish()
                }, ApiConsumer())

    }


}
