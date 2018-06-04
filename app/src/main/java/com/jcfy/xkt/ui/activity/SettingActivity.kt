package com.jcfy.xkt.ui.activity

import android.os.Bundle
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseActivity
import com.jcfy.xkt.setTitleText
import com.jcfy.xkt.utils.UserUtils
import com.lz.baselibrary.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * @author linzheng
 */
class SettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setTitleText("设置")

        tv_logout.setOnClickListener {
            UserUtils.clearUser()
            finish()
            ToastUtils.showToast("退出登录成功！")
        }
    }

}
