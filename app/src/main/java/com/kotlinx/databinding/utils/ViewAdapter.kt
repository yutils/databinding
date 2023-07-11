package com.kotlinx.databinding.utils

import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.bumptech.glide.Glide
import com.yujing.utils.YCheck

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
    fun repeat(view: TextView, repeat: String, count: Int? = 1) {
        var str = ""
        for (i in 0 until count!!)
            str += repeat + if (i == count - 1) "" else "\n"
        view.text = str
    }

    @BindingAdapter("int")
    @JvmStatic
    fun setInt(view: EditText, value: Int?) {
        view.setText(value.toString())
        view.setSelection(view.text.toString().length)
    }

    @InverseBindingAdapter(attribute = "int", event = "intAttrChanged")
    @JvmStatic
    fun getInt(view: EditText): Int {
        try {
            return view.text.toString().toInt()
        } catch (_: Exception) {
        }
        return 0
    }

    @BindingAdapter("intAttrChanged")
    @JvmStatic
    fun intChangeBind(view: EditText, inverseBindingListener: InverseBindingListener) {
        //设置双向绑定调用时机
        view.addTextChangedListener { //text->
            //if (text.toString().isEmpty()) return@addTextChangedListener
            inverseBindingListener.onChange()
        }
    }

    @BindingAdapter("double")
    @JvmStatic
    fun setDouble(view: EditText, value: Double?) {
        view.setText(value.toString())
        view.setSelection(view.text.toString().length)
    }

    @InverseBindingAdapter(attribute = "double", event = "doubleAttrChanged")
    @JvmStatic
    fun getDouble(view: EditText): Double {
        try {
            return view.text.toString().toDouble()
        } catch (_: Exception) {
        }
        return 0.0
    }

    @BindingAdapter("doubleAttrChanged")
    @JvmStatic
    fun doubleChangeBind(view: EditText, inverseBindingListener: InverseBindingListener) {
        //设置双向绑定调用时机
        view.addTextChangedListener { //text->
            inverseBindingListener.onChange()
        }
    }
}