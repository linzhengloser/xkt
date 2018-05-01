package com.jcfy.xkt.ui.activity

import android.content.Intent
import android.os.Bundle
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

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

    }
}
