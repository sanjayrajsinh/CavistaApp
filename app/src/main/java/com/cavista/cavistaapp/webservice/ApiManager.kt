package com.cavista.cavistaapp.webservice

import com.cavista.cavistaapp.BaseApp
import com.cavista.cavistaapp.appview.imagelist.model.ImageRes
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody


object ApiManager {
    
    fun findImageApiCall(imageName: String, apiCallback: ApiCallback<ImageRes>) {
        val observable = BaseApp
            .instance?.apiService?.apiInterface!!
            .getImages(imageName)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
        ApiHandle.createRetrofitBase(observable, apiCallback)
    }
  
}
