package com.jcfy.xkt.ui.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import com.jcfy.xkt.R
import com.jcfy.xkt.androidScheduler
import com.jcfy.xkt.api.ApiConsumer
import com.jcfy.xkt.api.UserApi
import com.jcfy.xkt.base.BaseActivity
import com.jcfy.xkt.utils.ApiFunction
import com.jcfy.xkt.utils.ValidateHandler
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.utils.ToastUtils
import com.lz.baselibrary.utils.Utils
import com.uber.autodispose.kotlin.autoDisposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_register.*

/**
 * @author linzheng
 */
class RegisterActivity : BaseActivity() {

    private lateinit var validateHandler: ValidateHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTranslucentStatus(true)
        setContentView(R.layout.activity_register)

        validateHandler = ValidateHandler(tv_send_validate_code)

        tv_send_validate_code.setOnClickListener {
            sendValidateCode()
        }

        tv_register.setOnClickListener {
            if (validate()) register()
        }

        tv_to_login.setOnClickListener {
            finish()
        }

        tv_user_protocol.setOnClickListener {
            it.tag = if (it.tag == "press") it.tag = "normal" else "press"
            val drawable = if (it.tag == "press")
                ContextCompat.getDrawable(this, R.drawable.login_choose_press) else
                ContextCompat.getDrawable(this, R.drawable.login_choose_normal)
            drawable?.setBounds(0, 0, drawable?.minimumWidth, drawable?.minimumHeight)
            tv_user_protocol.setCompoundDrawables(drawable, null, null, null)
        }

    }

    private fun validate(): Boolean {
        if (!Utils.isEmpty(arrayOf("手机号不能为空", "验证码不能为空", "密码不能为空"), et_phone, et_validate_number, et_pwd)) {
            return false
        } else if (tv_user_protocol.tag == "normal") {
            ToastUtils.showToast("请先同意用户协议")
            return false
        }
        return true

    }

    private fun sendValidateCode() {
        val phoneNumber = et_phone.text.toString().trim()
        if (TextUtils.isEmpty(phoneNumber)) {
            ToastUtils.showToast("请先输入手机号")
            return
        }
        validateHandler.startCountdown()
        val api = Api.createApi(UserApi::class)
        api.sendValidateCode(phoneNumber, "1")
                .doOnSubscribe { showLoadingDialog() }
                .doFinally { hideLoadingDialog() }
                .observeOn(androidScheduler)
                .map(ApiFunction())
                .autoDisposable(mScopeProvider)
                .subscribe(Consumer {
                    ToastUtils.showToast("发送成功")
                }, ApiConsumer())

    }

    private fun register() {
        val phoneNumber = et_phone.text.toString().trim()
        val pwd = et_pwd.text.toString().trim()
        val validateCode = et_validate_number.text.toString().trim()
        val api = Api.createApi(UserApi::class)
        api.register(phoneNumber, pwd, validateCode)
                .doOnSubscribe { showLoadingDialog() }
                .doFinally { hideLoadingDialog() }
                .observeOn(androidScheduler)
                .map(ApiFunction())
                .autoDisposable(mScopeProvider)
                .subscribe(Consumer {
                    ToastUtils.showToast("注册成功")
                    finish()
                }, ApiConsumer())

    }


    override fun onDestroy() {
        super.onDestroy()
        validateHandler.onDestroy()
    }

}
