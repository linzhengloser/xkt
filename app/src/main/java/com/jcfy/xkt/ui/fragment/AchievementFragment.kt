package com.jcfy.xkt.ui.fragment

import android.os.Bundle
import android.view.View
import com.jcfy.xkt.R
import com.jcfy.xkt.dp
import com.jcfy.xkt.getResourceColor
import com.jcfy.xkt.module.mine.Achievement
import com.jcfy.xkt.module.mine.AchievementWrapper
import com.jcfy.xkt.ui.multitype.mine.AchievementItemViewBinder
import com.lz.baselibrary.view.VerticalItemDecoration
import kotlinx.android.synthetic.main.fragment_schedule.*
import me.drakeet.multitype.register
import org.greenrobot.eventbus.Subscribe

/**
 * @author linzheng
 */
class AchievementFragment : PrimaryOrIntermediateFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_schedule.addItemDecoration(VerticalItemDecoration(1.dp(),context?.getResourceColor(R.color.itemDividerColor)!!))
        mAdapter.register(Achievement::class,AchievementItemViewBinder(mType))
    }

    @Subscribe
    public fun onMessageEvent(event: AchievementWrapper) {
        if ((mType == TYPE_PRIMARY && event.baseHistory.isEmpty()) || (mType == TYPE_INTERMEDIATE && event.middleHistory.isEmpty())) {
            showEmptyDataLayout()
            return
        }
        if (mType == TYPE_PRIMARY) {
            initData(event.baseHistory)
        } else {
            initData(event.middleHistory)
        }
        showSuccessLayout()
        srl_schedule.refreshComplete()
        mAdapter.notifyDataSetChanged()
    }

    private fun initData(list: List<Achievement>) {
        list.forEach {
            mItems.add(it)
        }
    }

    companion object {
        fun newInstance(type: Int): AchievementFragment {
            val args = Bundle()
            args.putInt("type", type)
            val fragment = AchievementFragment()
            fragment.arguments = args
            return fragment
        }
    }

}