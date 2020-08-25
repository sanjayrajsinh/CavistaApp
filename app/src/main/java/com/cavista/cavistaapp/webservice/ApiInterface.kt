package com.cavista.cavistaapp.webservice

import com.cavista.cavistaapp.appview.imagelist.model.ImageRes
import com.google.gson.JsonObject
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*


interface ApiInterface {

    @GET("gallery/search/1")
    fun getImages(@Query("q") data:String): Observable<ImageRes>

}
