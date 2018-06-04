package com.jcfy.xkt.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseListFragment
import com.jcfy.xkt.base.LoadListData
import com.jcfy.xkt.module.mine.Schedule

/**
 * @author linzheng
 */
class ScheduleFragment : BaseListFragment(), LoadListData {

    private var mTotalSchedule = "0%"

    private lateinit var mScheduleList: List<Schedule>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createViewByLoadSir(inflater.inflate(R.layout.fragment_schedule, container, false))
    }

    override fun loadData() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadListData()
    }

    override fun loadListData(isInitialize: Boolean, isRefresh: Boolean) {
    }


    companion object {
        fun newInstance(): ScheduleFragment {
            val args = Bundle()
            val fragment = ScheduleFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
