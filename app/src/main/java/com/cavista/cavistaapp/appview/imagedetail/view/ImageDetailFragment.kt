package com.cavista.cavistaapp.appview.imagedetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.cavista.cavistaapp.R
import com.cavista.cavistaapp.appview.imagedetail.adapter.CommentAdapter
import com.cavista.cavistaapp.appview.imagedetail.viewmodel.ImageDetailViewModel
import com.cavista.cavistaapp.appview.imagelist.model.Data
import com.cavista.cavistaapp.database.ImageComment
import com.cavista.cavistaapp.databinding.FragmentImageDetailBinding
import com.cavista.cavistaapp.utils.DialogUtils
import kotlinx.android.synthetic.main.fragment_image_detail.*
import kotlinx.android.synthetic.main.fragment_image_detail.view.*

class ImageDetailFragment : Fragment() {

    private lateinit var viewModel:ImageDetailViewModel
    private lateinit var adapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(ImageDetailViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentImageDetailBinding>(LayoutInflater.from(container!!.context), R.layout.fragment_image_detail, container, false)
        binding.detailVariable = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeData()
        initializeRecycleView()

        view.btn_send.setOnClickListener {
            if(et_comment.text!=null && et_comment.text.toString().isNotBlank()) {
                viewModel.insertComment(viewModel.imageDetail?.id!!, et_comment.text!!.toString());
                et_comment.setText("")
            }else{
                DialogUtils.toast(context,"Please write your comment")
            }
        }
    }

    private fun initializeData(){
        arguments?.let {
            viewModel.imageDetail = it.getParcelable("data")
        }
        Glide.with(context!!).load(viewModel.imageDetail?.images?.get(0)?.link)
            .placeholder(R.drawable.ic_placeholder).into(imageview)

        viewModel.commentList?.observe(this, Observer {
            if(it!=null){
                adapter.setData(it as ArrayList<ImageComment>)
            }
        })
    }

    private fun initializeRecycleView() {
        viewModel.getAllCommentById(viewModel.imageDetail?.id!!)
        adapter = CommentAdapter()
        rv_comments.layoutManager = LinearLayoutManager(context)
        rv_comments.adapter = adapter
    }

}