package com.jcfy.xkt.ui.activity.mine

import android.os.Bundle
import com.jcfy.xkt.androidScheduler
import com.jcfy.xkt.api.ApiConsumer
import com.jcfy.xkt.api.MineApi
import com.jcfy.xkt.setTitleText
import com.jcfy.xkt.ui.fragment.WrongOrCollectionQuestionFragment
import com.jcfy.xkt.ui.itemdecoration.MyViewPagerAdapter
import com.jcfy.xkt.utils.ApiFunction
import com.lz.baselibrary.network.Api
import com.uber.autodispose.kotlin.autoDisposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_schedule.*
import org.greenrobot.eventbus.EventBus

/**
 * @author linzheng
 */
class WrongOrCollectionQuestionActivity : PrimaryOrIntermediateActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleText(if(intent.getIntExtra("type",1) == 1) "错题" else "收藏")
        vp_schedule.adapter = MyViewPagerAdapter(
                supportFragmentManager,
                listOf(
                        WrongOrCollectionQuestionFragment.newInstance(1),
                        WrongOrCollectionQuestionFragment.newInstance(2)
                )
        )
        getData()
    }

    override fun getData() {
        val api = Api.createApi(MineApi::class)
        api.getWrongQuestion()
                .map(ApiFunction())
                .observeOn(androidScheduler)
                .autoDisposable(mScopeProvider)
                .subscribe(Consumer {
                    EventBus.getDefault().post(it)
                }, ApiConsumer())
    }

}
