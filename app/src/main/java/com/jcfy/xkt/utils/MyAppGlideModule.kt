package com.jcfy.xkt.utils

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.jcfy.xkt.R

/**
 * @author linzheng
 */
@GlideModule
class MyAppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDefaultRequestOptions(RequestOptions().centerCrop().error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher))
    }

}
