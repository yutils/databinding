package com.kotlinx.databinding.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlinx.databinding.R
import com.kotlinx.databinding.databinding.ActivityListItemBinding
import com.yujing.utils.YToast


/**
 * databinding demo
 * @author 余静 2022年2月18日16:55:11
 */
class CarAdapter(var data: List<Car>?) : RecyclerView.Adapter<CarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.activity_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.binding.car = data?.get(position)
        holder.binding.iv.setOnClickListener {
            YToast.show("点击：" + data?.get(position)?.name)
        }
        //必须要有这行，防止闪烁
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }
}

class CarViewHolder(var binding: ActivityListItemBinding) : RecyclerView.ViewHolder(binding.root) {}