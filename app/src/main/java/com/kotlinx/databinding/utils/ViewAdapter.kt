package com.kotlinx.databinding.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ViewAdapter {
    //加载图片,会将setImageUrl注入到布局文件xml的ImageView中，举例： app:setImageUrl='@{"http://dingyue.ws.126.net/2022/0330/b86f4d0fj00r9iqwg001vc000hs011xc.jpg"}'/>
    @BindingAdapter("android:setImageUrl")
    @JvmStatic
    fun setImageUrl(view: ImageView, url: String?) {
        Glide.with(view).load(url).into(view)
    }

    //重复显示多次字符串
    @BindingAdapter("android:repeat", "android:count")
    @JvmStatic
    fun repeat(view: TextView, value: String, count: Int? = 1) {
        var str = ""
        for (i in 0 until count!!)
            str += value + if (i == count - 1) "" else "\n"
        view.text = str
    }
}