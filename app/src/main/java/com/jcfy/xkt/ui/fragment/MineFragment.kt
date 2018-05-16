package com.jcfy.xkt.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseListFragment
import com.jcfy.xkt.module.Mine
import com.jcfy.xkt.module.User
import com.jcfy.xkt.ui.activity.BasicInformationActivity
import com.jcfy.xkt.ui.activity.MessageCenterActivity
import com.jcfy.xkt.ui.activity.RechargeCenterActivity
import com.jcfy.xkt.ui.activity.SettingActivity
import com.jcfy.xkt.ui.dialog.ShareDialog
import com.jcfy.xkt.ui.multitype.MineHeaderItemViewBinder
import com.jcfy.xkt.ui.multitype.MineItemViewBinder
import com.lz.baselibrary.view.RefreshListener
import kotlinx.android.synthetic.main.fragment_mine.*
import me.drakeet.multitype.register
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.support.v4.startActivity

/**
 * @author linzheng
 */

const val BASIC_INFORMATION = "基本信息"

const val MESSAGE_CENTER = "消息中心"

const val SETTING = "设置"

const val SHARE = "分享"

const val RECHARGE_CENTER = "充值中心"

class MineFragment : BaseListFragment(), RefreshListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    private val mMineList = listOf(
            Mine(R.drawable.mine_basic_information, BASIC_INFORMATION),
            Mine(R.drawable.mine_recharge_center, RECHARGE_CENTER),
            Mine(R.drawable.mine_message_center, MESSAGE_CENTER),
            Mine(R.drawable.mine_help, "使用帮助"),
            Mine(R.drawable.mine_opinion, "意见反馈"),
            Mine(R.drawable.mine_contact, "联系我们"),
            Mine(R.drawable.mine_share, SHARE),
            Mine(R.drawable.mine_setting, SETTING)
    )

    override fun loadData() {
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mAdapter.register(User::class, MineHeaderItemViewBinder())
        mAdapter.register(Mine::class, MineItemViewBinder())

        mItems.add(User())
        mItems.addAll(mMineList)

        rv_mine.adapter = mAdapter
        rv_mine.layoutManager = LinearLayoutManager(context)

        srl_mine.setRefreshListener(this)
        srl_mine.isLoadMoreEnable(false)
        mAdapter.notifyDataSetChanged()
    }

    override fun refresh(isRefresh: Boolean) {
        object : Handler() {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                srl_mine.refreshComplete()
            }
        }.sendEmptyMessageDelayed(1, 2000)
    }


    @Subscribe
    public fun onMessageEvent(mine: Mine) {
        when (mine.title) {
            BASIC_INFORMATION -> startActivity(Intent(context, BasicInformationActivity::class.java))
            MESSAGE_CENTER -> startActivity(Intent(context, MessageCenterActivity::class.java))
            SHARE -> ShareDialog().show(childFragmentManager)
            SETTING -> startActivity<SettingActivity>()
            RECHARGE_CENTER -> startActivity<RechargeCenterActivity>()
        }
    }

    companion object {
        fun newInstance(): MineFragment {
            val args = Bundle()
            val fragment = MineFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
