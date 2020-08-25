package com.cavista.cavistaapp.appview.imagelist.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cavista.cavistaapp.R
import com.cavista.cavistaapp.appview.MainActivity
import com.cavista.cavistaapp.appview.imagedetail.view.ImageDetailFragment
import com.cavista.cavistaapp.appview.imagelist.model.Data
import kotlinx.android.synthetic.main.row_item_image.view.*

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.CustomViewHolder>() {

    var dataList = ArrayList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var view =  LayoutInflater.from(parent.context).inflate(R.layout.row_item_image, parent, false)
        return CustomViewHolder(view)
    }

    fun setData(data: ArrayList<Data>?) {

        if (data != null) {
            this.dataList.clear()
            this.dataList.addAll(data)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(dataList.get(position))
    }

    inner class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Data) {
            Glide.with(view).load(item.images?.get(0)?.link).placeholder(R.drawable.ic_placeholder)
                .into(view.img_row_item)

            view.img_row_item.setOnClickListener {
                var bundle = Bundle()
                bundle.putParcelable("data", item)
                MainActivity.getInstance(view.context)?.addFragment(view.context,ImageDetailFragment(), bundle)
            }
        }
    }
}