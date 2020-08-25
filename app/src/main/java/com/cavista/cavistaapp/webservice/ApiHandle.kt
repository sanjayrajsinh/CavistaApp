package com.cavista.cavistaapp.webservice

import com.cavista.cavistaapp.BaseApp
import com.google.gson.Gson
import com.photoshoot.photoshootapp.utils.broadcasts.ConnectivityUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.EOFException

object ApiHandle {

    fun <T> createRetrofitBase(
            observable: Observable<T>,
            apiCallback: ApiCallback<T>
    ) {
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<T>() {
                    override fun onNext(t: T) {
                            apiCallback.onSuccess(t)
                    }

                    override fun onError(error: Throwable) {
                            var responseModel: ApiErrorModel? = null
                            when (error) {
                                    is HttpException -> try {
                                            val response = error.response()
                                            when {
                                                    response!!.code() >= 500 -> {
                                                            responseModel = ApiErrorModel()
                                                            responseModel.error = response.message()
                                                            responseModel.status = response.code().toString()
                                                            responseModel.message = response.message()
                                                    }
                                                    else -> {
                                                            val gson = Gson()
                                                            val responseString = response.errorBody()!!.string()
                                                            responseModel = ApiErrorModel()
                                                            responseModel.error = response.message()
                                                            responseModel.status = response.code().toString()
                                                            responseModel.message = response.message()
                                                            var errormessage = Response("")
                                                            try {
                                                                    errormessage =
                                                                            gson.fromJson<Response>(
                                                                                    responseString,
                                                                                    Response::class.java
                                                                            )
                                                                    responseModel.message = errormessage.message
                                                            } catch (e: java.lang.Exception) {
                                                                    e.printStackTrace()
                                                            }
                                                    }
                                            }
                                    } catch (e: Exception) {
                                            e.printStackTrace()
                                    }
                                    is EOFException -> {
                                            responseModel = ApiErrorModel()
                                            responseModel.error = "Request timeout."
                                            responseModel.status = 408.toString()
                                            responseModel.message = "Request timeout."
                                    }
                                    else -> {
                                            if (!ConnectivityUtils.isNetworkAvailable(BaseApp.instance!!.baseContext)) {
                                                    responseModel = ApiErrorModel()
                                                    responseModel.error = "Internet connection appears to be offline."
                                                    responseModel.status = 600.toString()
                                                    responseModel.message = "Internet connection appears to be offline."
                                            } else {
                                                    responseModel = ApiErrorModel()
                                                    responseModel.error = "Could not connect to the server."
                                                    responseModel.status = 600.toString()
                                                    responseModel.message = "Could not connect to the server."
                                            }
                                    }
                            }
                            apiCallback.onFailure(responseModel!!)
                    }

                    override fun onComplete() {
                    }
            })
    }

}
