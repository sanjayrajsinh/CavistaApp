package com.cavista.cavistaapp.appview.imagedetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cavista.cavistaapp.BaseApp
import com.cavista.cavistaapp.appview.imagelist.model.Data
import com.cavista.cavistaapp.database.ImageComment
import com.cavista.cavistaapp.database.ImageDao

class ImageDetailViewModel : ViewModel() {

    var commentList: LiveData<List<ImageComment>>? = MutableLiveData()
    private var imageDao: ImageDao? = null
    var imageDetail: Data? = null


    init {
        imageDao = BaseApp.daoInstance?.imageDao()
    }

    fun getAllCommentById(imageId: String) {
        if (imageDao != null) {
            commentList = imageDao!!.getAllComment(imageId)
        }
    }

    fun insertComment(id: String, comment: String) {
        var data = ImageComment(imageID = id, comment = comment)
        imageDao?.insert(data)
    }

}