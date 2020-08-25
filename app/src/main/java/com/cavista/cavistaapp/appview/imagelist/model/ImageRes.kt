package com.cavista.cavistaapp.appview.imagelist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageRes(
    @SerializedName("data")
    var data: ArrayList<Data>? = arrayListOf(),

    @SerializedName("success")
    var success: Boolean? = false,

    @SerializedName("status")
    var status: Int? = 0
) : Parcelable