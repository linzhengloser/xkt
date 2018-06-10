package com.jcfy.xkt.ui.activity.mine

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseListActivity
import com.jcfy.xkt.dp
import com.jcfy.xkt.module.BasicInformation
import com.jcfy.xkt.setTitleText
import com.jcfy.xkt.ui.multitype.mine.BasicInformationItemViewBinder
import com.jcfy.xkt.utils.UserUtils
import com.lz.baselibrary.view.VerticalItemDecoration
import kotlinx.android.synthetic.main.activity_basic_information.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class BasicInformationActivity : BaseListActivity() {


    private val mBasicInformationList = listOf(
            BasicInformation("昵称", "Loser"),
            BasicInformation("性别", "男"),
            BasicInformation("手机号", "13477777777"),
            BasicInformation("生日", "2018-04-04"),
            BasicInformation("城市", "武汉"),
            BasicInformation("修改密码", "")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_information)

        setTitleText("基本信息")
        mAdapter.register(BasicInformation::class, BasicInformationItemViewBinder())
        srl_basic_information.isLoadMoreEnable(false)
        rv_basic_information.layoutManager = LinearLayoutManager(this)
        rv_basic_information.addItemDecoration(VerticalItemDecoration(1.dp(), resources.getColor(R.color.itemDividerColor)))
        rv_basic_information.adapter = mAdapter
        rv_basic_information.postDelayed({
            val user = UserUtils.user!!
            mItems.add(BasicInformation("昵称", user.nikeName))
            mItems.add(BasicInformation("性别", getSex(user.sex)))
            mItems.add(BasicInformation("手机号", user.mobile))
            mItems.add(BasicInformation("生日", user.birthDate))
            mItems.add(BasicInformation("城市", user.city))
            mItems.add(BasicInformation("修改密码", ""))
            showSuccessLayout()
            mAdapter.notifyDataSetChanged()
        }, 1000)
    }

    private fun getSex(sex: String) = when (sex) {
        "1" -> "男"
        "2" -> "女"
        else -> "保密"
    }

}
