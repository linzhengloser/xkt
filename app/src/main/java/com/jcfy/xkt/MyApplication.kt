package com.jcfy.xkt

import com.jcfy.xkt.ui.loadsir.EmptyCallback
import com.jcfy.xkt.ui.loadsir.ErrorCallback
import com.jcfy.xkt.ui.loadsir.LoadingCallback
import com.jcfy.xkt.utils.TokenInterceptor
import com.kingja.loadsir.core.LoadSir
import com.lz.baselibrary.LibraryApplication
import com.lz.baselibrary.network.Api
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import timber.log.Timber.DebugTree


/**
 * @author linzheng
 */
class MyApplication : LibraryApplication() {

    override fun onCreate() {
        super.onCreate()
        Api.BASE_URL = "http://wx.119kst.com/client/"

        LoadSir.beginBuilder()
                .addCallback(LoadingCallback())
                .addCallback(ErrorCallback())
                .addCallback(EmptyCallback())
                .setDefaultCallback(LoadingCallback::class.java)
                .commit()
        Timber.plant(DebugTree())
    }

    override fun buildInterceptor(): List<Interceptor> {
        return listOf(
                TokenInterceptor(),
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                    Timber.tag("OkHttp").d(it)
                }).setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        )
    }

}