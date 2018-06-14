package com.jcfy.xkt.ui.activity

import android.os.Bundle
import com.jcfy.xkt.R
import com.jcfy.xkt.androidScheduler
import com.jcfy.xkt.api.ApiConsumer
import com.jcfy.xkt.api.MineApi
import com.jcfy.xkt.api.Response
import com.jcfy.xkt.base.BaseActivity
import com.jcfy.xkt.module.mine.HelpWrapper
import com.jcfy.xkt.setTitleText
import com.jcfy.xkt.utils.ApiFunction
import com.lz.baselibrary.network.Api
import com.uber.autodispose.kotlin.autoDisposable
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_help.*

/**
 * 关于我们 or 使用帮助
 * @author linzheng
 */

const val TYPE_ABOUT_US = 1

const val TYPE_USE_HELP = 2

class HelpActivity : BaseActivity() {

    private var mType:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        mType = intent.getIntExtra("type",1)
        setTitleText(when (mType) {
                    TYPE_ABOUT_US -> "关于我们"
                    TYPE_USE_HELP -> "使用帮助"
                    else -> "网页"
        })
        val api = Api.createApi(MineApi::class)
        getHelp(when(mType){
            TYPE_USE_HELP -> api.getUsingHelp()
            TYPE_ABOUT_US -> api.getAboutUs()
            else -> throw RuntimeException()
        })
    }


    private fun getHelp(observable: Observable<Response<HelpWrapper>>) {
        observable
                .map(ApiFunction())
                .doOnSubscribe { showLoadingDialog() }
                .doFinally { hideLoadingDialog() }
                .observeOn(androidScheduler)
                .autoDisposable(mScopeProvider)
                .subscribe(Consumer {
                    wv_help.settings.defaultTextEncodingName = "UTF-8"
                    wv_help.loadData(it.help.content, "text/html; charset=UTF-8", null)
                }, ApiConsumer())
    }

}
