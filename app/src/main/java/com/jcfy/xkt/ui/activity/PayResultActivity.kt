package com.jcfy.xkt.ui.activity

import android.os.Bundle
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_pay_result.*

/**
 * @author linzheng
 */
class PayResultActivity : BaseActivity(){

    private var isSuccess: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_result)
        isSuccess = intent.getBooleanExtra("isSuccess",true)
        if(isSuccess) iv_icon.setImageResource(R.drawable.pay_result_success)
        else iv_icon.setImageResource(R.drawable.pay_result_failed)
    }

}