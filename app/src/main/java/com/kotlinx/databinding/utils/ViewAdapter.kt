package com.kotlinx.databinding.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ViewAdapter {
    //加载图片,会将setImageUrl注入到布局文件xml的ImageView中，举例： app:setImageUrl='@{"http://pic1.win4000.com/wallpaper/2020-10-19/5f8d2f350b621.jpg"}'/>
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