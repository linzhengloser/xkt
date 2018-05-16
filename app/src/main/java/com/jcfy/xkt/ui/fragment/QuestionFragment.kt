package com.jcfy.xkt.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcfy.xkt.R
import com.jcfy.xkt.base.BaseFragment

/**
 * @author linzheng
 */
class QuestionFragment : BaseFragment() {

    override fun loadData() {
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_question,container,false)
    }

}
