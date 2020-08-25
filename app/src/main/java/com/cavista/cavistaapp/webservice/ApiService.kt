package com.cavista.cavistaapp.webservice

import com.cavista.cavistaapp.BuildConfig
import com.cavista.cavistaapp.webservice.ApiConstant.DEFAULT_TIMEOUT
import com.cavista.cavistaapp.webservice.ApiConstant.HEADER_KEY
import com.cavista.cavistaapp.webservice.ApiConstant.HEADER_VALUE
import com.cavista.cavistaapp.webservice.ApiConstant.HTTP_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiService {

    var apiInterface: ApiInterface? = null

    init {
        initSecureRetrofitService()
    }

    private fun initSecureRetrofitService() {

        try {
            val builder = OkHttpClient.Builder()

            //set default time out
            builder.connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            builder.readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)

            addInterceptor(builder);
            addHeaders(builder)

            val retrofit = Retrofit.Builder()
                .baseUrl(HTTP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build()

            apiInterface = retrofit.create<ApiInterface>(ApiInterface::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    // For api logs  only
    private fun addInterceptor(builder : OkHttpClient.Builder) {
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        } else {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.NONE
            builder.addInterceptor(logging)
        }
    }

    private fun addHeaders(builder: OkHttpClient.Builder) {
        builder.addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.addHeader(HEADER_KEY, HEADER_VALUE)
            chain.proceed(requestBuilder.build())
        }
    }

}
