package com.kotlinx.databinding.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.kotlinx.databinding.Bd
import com.kotlinx.databinding.R
import com.kotlinx.databinding.list.ListActivity
import com.yujing.utils.YUtils

/**
 * databinding demo
 * @author 余静 2022年2月18日16:52:35
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: Bd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        YUtils.init(application)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        var user = User("张三", "男", 29)
        binding.user = user

        //修改名称
        binding.bt1.setOnClickListener {
            user.name = "李四"
        }

        //年龄+1
        binding.bt2.setOnClickListener {
            user.age++
        }

        //获取json
        binding.bt3.setOnClickListener {
            val json = Gson().toJson(binding.user)
            Log.i("josn", json)
            Toast.makeText(this, json, Toast.LENGTH_LONG).show()
        }
    }

    fun next(v: View){
       startActivity(Intent(this,ListActivity::class.java))
    }
}
