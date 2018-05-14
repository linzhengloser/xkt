package com.jcfy.xkt.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseListFragment
import com.jcfy.xkt.module.Information
import com.jcfy.xkt.ui.multitype.MainHeaderItemViewBinder
import com.jcfy.xkt.ui.multitype.MainInformationItemViewBinder
import kotlinx.android.synthetic.main.fragment_main.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class MainFragment : BaseListFragment() {
    override fun loadData() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mItems.add("MainHeader")
        repeat(10) {
            mItems.add(Information())
        }

        mAdapter.register(String::class, MainHeaderItemViewBinder())
        mAdapter.register(Information::class, MainInformationItemViewBinder())

        rv_main.layoutManager = LinearLayoutManager(context)
        rv_main.adapter = mAdapter

        srl_main.isLoadMoreEnable(false)

    }

    companion object {
        fun newInstance(): MainFragment {
            val args = Bundle()
            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }

}