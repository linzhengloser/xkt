package com.jcfy.xkt.ui.activity

import android.os.Bundle
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseActivity
import com.jcfy.xkt.setTitleText
import kotlinx.android.synthetic.main.activity_examination_result.*

/**
 * @author linzheng
 */
class ExaminationResultActivity : BaseActivity() {

    var mIsSuccess: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_examination_result)

        setTitleText("考试")
        mIsSuccess = intent.getBooleanExtra("isSuccess", true)
        if (mIsSuccess) iv_icon.setImageResource(R.drawable.examination_result_success)
        else iv_icon.setImageResource(R.drawable.examination_result_failed)

    }


}
