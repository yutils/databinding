package com.kotlinx.databinding.main

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField


//如果变量改变就更新view，那么需要实现BaseObservable并且，在set中调用notifyChange()
class User : BaseObservable {
    //普通属性，修改完后需要调用user.notifyChange()
    var id = "1"

    //（推荐）set中写入notifyChange()
    var name: String? = ""
        set(value) {
            field = value
            notifyChange()
        }


    //ObservableField 类型的字段，双向绑定使用中，当在xml中值收到改变时，会刷新页面
    //缺点，获取json或者反射时ObservableField会成为一个对象，类似map
    var sex: ObservableField<String> = ObservableField<String>()


    //int 类型不能双向绑定，因为xml赋值只能是String
    var age: Int = 0
        set(value) {
            if (field == value) return
            field = value
            ageStr = value.toString()
        }

    @Transient //ageStr 去更改界面，不会被序列化
    var ageStr = age.toString()
        set(value) {
            if (field == value) return
            field = value
            age = value.toIntOrNull() ?: 0
            notifyChange()
        }

    constructor(name: String?, sex: String?, age: Int) {
        this.name = name
        this.sex.set(sex)
        this.age = age
    }
}