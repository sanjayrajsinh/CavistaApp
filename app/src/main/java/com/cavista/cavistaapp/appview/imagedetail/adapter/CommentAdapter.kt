package com.cavista.cavistaapp.appview.imagedetail.adapter

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
import com.cavista.cavistaapp.database.ImageComment
import kotlinx.android.synthetic.main.row_item_comment.view.*
import kotlinx.android.synthetic.main.row_item_image.view.*

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CustomViewHolder>() {

    var dataList = ArrayList<ImageComment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var view =  LayoutInflater.from(parent.context).inflate(R.layout.row_item_comment, parent, false)
        return CustomViewHolder(view)
    }

    fun setData(data: ArrayList<ImageComment>?) {
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
        fun bind(item: ImageComment) {
            view.txt_comment.text=item.comment
        }
    }
}