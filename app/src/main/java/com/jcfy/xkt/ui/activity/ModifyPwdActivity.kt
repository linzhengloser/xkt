package com.jcfy.xkt.ui.activity

import android.os.Bundle
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseActivity
import com.jcfy.xkt.setTitleText

/**
 * @author linzheng
 */
class ModifyPwdActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_pwd)
        setTitleText("修改密码")
    }

}
