package com.jcfy.xkt.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.Html
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

/**
 * @author linzheng
 */
class GlideImageGetter(private val context: Context) : Html.ImageGetter {


    override fun getDrawable(source: String?): Drawable {
        var drawable: UrlDrawable = UrlDrawable()
        GlideApp.with(context).asBitmap().load(source).into(object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                drawable.urlBitmap = resource
            }
        })
        return drawable
    }

    class UrlDrawable : BitmapDrawable() {

        var urlBitmap: Bitmap? = null

        override fun draw(canvas: Canvas?) {
            if (urlBitmap != null)
                canvas?.drawBitmap(urlBitmap, 0f, 0f, paint)
        }

    }

}
