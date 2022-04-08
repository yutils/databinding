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
class CarAdapter<T>(var data: List<T>?) : RecyclerView.Adapter<CarAdapter.MyViewHolder>() {
    //ViewHolder
    class MyViewHolder(var binding: ActivityListItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.activity_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data?.get(position) as Branditem
        holder.binding.branditem = item
        holder.binding.iv.setOnClickListener { YToast.show("点击：" + item.name) }

        //必须要有这行，防止闪烁
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }
}
