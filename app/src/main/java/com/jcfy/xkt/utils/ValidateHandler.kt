package com.jcfy.xkt.utils

import android.os.Handler
import android.os.Message
import android.widget.TextView

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/02/06
 * desc   : 倒计时 Handler
 * version: 1.0
</pre> *
 */
class ValidateHandler(private val mTextView: TextView) : Handler() {

    private var mTime = 60

    override fun handleMessage(msg: Message) {
        mTextView.isClickable = false
        mTextView.text = (--mTime).toString() + "S后重试"
        if (mTime == 0) {
            mTime = 60
            mTextView.text = "发送验证码"
            mTextView.isClickable = true
            return
        }
        sendEmptyMessageDelayed(1, 1000)
    }

    fun startCountdown() {
        sendEmptyMessage(1)
    }

    fun onDestroy() {
        removeMessages(1)
    }

}
