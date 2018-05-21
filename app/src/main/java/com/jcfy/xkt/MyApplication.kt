package com.jcfy.xkt

import com.jcfy.xkt.ui.loadsir.EmptyCallback
import com.jcfy.xkt.ui.loadsir.ErrorCallback
import com.jcfy.xkt.ui.loadsir.LoadingCallback
import com.kingja.loadsir.core.LoadSir
import com.lz.baselibrary.LibraryApplication
import com.lz.baselibrary.network.Api

/**
 * @author linzheng
 */
class MyApplication : LibraryApplication() {

    override fun onCreate() {
        super.onCreate()
        Api.baseUrl = "http://115.159.109.107:80/client/"

        LoadSir.beginBuilder()
                .addCallback(LoadingCallback())
                .addCallback(ErrorCallback())
                .addCallback(EmptyCallback())
                .setDefaultCallback(LoadingCallback::class.java)
                .commit()

    }

}