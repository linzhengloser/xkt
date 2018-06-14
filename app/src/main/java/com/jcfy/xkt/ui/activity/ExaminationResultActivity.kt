package com.jcfy.xkt.ui.activity

import android.os.Bundle
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseActivity
import com.jcfy.xkt.module.ExaminationResult
import com.jcfy.xkt.setTitleText
import kotlinx.android.synthetic.main.activity_examination_result.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author linzheng
 */
class ExaminationResultActivity : BaseActivity() {

    private var mIsSuccess: Boolean = true

    private lateinit var mResult: ExaminationResult

    private val mSimpleDateFormat = SimpleDateFormat("mm分ss秒", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("GMT+0")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_examination_result)
        setTitleText("考试")
        mResult = intent.getParcelableExtra("result")
        mIsSuccess = intent.getBooleanExtra("isSuccess", true)

        if (mResult.achievement >= 60f) {
            //及格
            iv_icon.setImageResource(R.drawable.examination_result_success)
            tv_achievement.text = "恭喜${mResult.achievement}分,考试完成！"
        } else {
            //不及格
            iv_icon.setImageResource(R.drawable.examination_result_failed)
            tv_achievement.text = "很遗憾${mResult.achievement}分,考试不合格！"
        }
        tv_time.text = "总共${mResult.totalQuestionCount}题，对${mResult.rightQuestionCount}题,错${mResult.wrongQuestionCount}题,\n考试时长${mSimpleDateFormat.format(Date(mResult.totalTime))}，实用时长${mSimpleDateFormat.format(Date(mResult.alreadyUseTime))}"
    }
}
