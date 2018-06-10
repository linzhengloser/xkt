package com.jcfy.xkt.ui.activity.mine

import android.os.Bundle
import android.support.v7.widget.AppCompatTextView
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseActivity
import com.jcfy.xkt.ui.fragment.EVENT_REFRESH
import com.jcfy.xkt.utils.selection.SelectionAdapter
import com.jcfy.xkt.utils.selection.TabTextViewSectionBinder
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.layout_tab.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * @author linzheng
 */
abstract class PrimaryOrIntermediateActivity : BaseActivity(){

    private val mSelectionAdapter = SelectionAdapter().apply {
        register(AppCompatTextView::class.java, TabTextViewSectionBinder())
        singleSelection()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        mSelectionAdapter.bindViewPager(vp_schedule,ll_tab)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    public fun onRefreshScheduleEvent(event: String) {
        if(event == EVENT_REFRESH)
            getData()
    }

    abstract fun getData()

}