package com.cavista.cavistaapp.appview.imagelist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cavista.cavistaapp.appview.imagelist.model.Data

class ImageListViewModel() : ViewModel() {

    var list: MutableLiveData<ArrayList<Data>> = MutableLiveData()

    init {
        var appoList = ArrayList<Data>()
        list.postValue(appoList)
    }

}