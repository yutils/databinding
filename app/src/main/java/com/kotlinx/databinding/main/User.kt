package com.kotlinx.databinding.main

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableByte
import androidx.databinding.ObservableChar
import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt
import androidx.databinding.ObservableLong
import androidx.databinding.ObservableParcelable
import androidx.databinding.ObservableShort
import com.kotlinx.databinding.BR

//如果变量改变就更新view，那么需要实现BaseObservable并且，在set中调用notifyChange()
class User : BaseObservable {
    //普通属性，修改完后需要调用user.notifyChange()
    var id = "1"

    //set中写入,notifyChange()刷新
    @Bindable//有了注解这个才会生成BR.name
    var name: String? = ""
        set(value) {
            field = value
//            notifyChange()
            notifyPropertyChanged(BR.name)
        }

    var nickName = ObservableField<String>("小李")

    var height = ObservableInt(176)
// 或者
//    var height: Int = 176
//        set(value) {
//            if (field == value) return
//            field = value
//            notifyChange()
//        }

    //ObservableField 类型的字段，双向绑定使用中，当在xml中值收到改变时，会刷新页面
    //缺点，获取json或者反射时ObservableField会成为一个对象，类似map
    var sex = ObservableField<String>()


//    var abc1 = ObservableBoolean(true)
//    var abc2 = ObservableDouble(1.234)
//    var abc3 = ObservableByte(0xFF.toByte())
//    var abc4 = ObservableChar('A')
//    var abc5 = ObservableShort(123)
//    var abc6 = ObservableInt(123456)
//    var abc7 = ObservableLong(1234567890123456)
//    var abc8 = ObservableFloat(0.123F)
//    var abc9 = ObservableParcelable<ObservableFloat>().apply {
//        set(ObservableFloat(0.123F))
//    }
//    var abc10 = ObservableArrayMap<String, String>().apply {
//        set("A", "123")
//        set("B", "456")
//    }
//    var abc11 = ObservableArrayList<String>().apply {
//        add("123")
//        add("456")
//    }

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