package com.jcfy.xkt.ui.fragment

import android.os.Bundle
import android.view.View
import com.jcfy.xkt.module.Chapter
import com.jcfy.xkt.module.mine.Schedule
import com.jcfy.xkt.module.mine.ScheduleWrapper
import com.jcfy.xkt.ui.multitype.mine.ScheduleChildItemViewBinder
import com.jcfy.xkt.ui.multitype.mine.ScheduleHeaderItemViewBinder
import com.jcfy.xkt.ui.multitype.mine.ScheduleParentItemViewBinder
import me.drakeet.multitype.register
import org.greenrobot.eventbus.Subscribe

/**
 * @author linzheng
 */
class ScheduleFragment : PrimaryOrIntermediateQuestionScheduleFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter.apply {
            register(ScheduleWrapper::class, ScheduleHeaderItemViewBinder())
            register(Schedule::class, ScheduleParentItemViewBinder(this@ScheduleFragment))
            register(Chapter::class, ScheduleChildItemViewBinder())
        }
    }

    @Subscribe
    public override fun onScheduleEvent(event: ScheduleWrapper) {
        mItems.add(event)
        super.onScheduleEvent(event)
    }

    companion object {
        fun newInstance(type: Int): ScheduleFragment {
            val args = Bundle()
            args.putInt("type", type)
            val fragment = ScheduleFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
