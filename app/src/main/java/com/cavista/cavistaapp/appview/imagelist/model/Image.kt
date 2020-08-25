package com.cavista.cavistaapp.appview.imagelist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    var id: String? = "",
    var title: String? = "",
    var description: String? = "",
    var type: String? = "",
    var width: Int? = 0,
    var height: Int? = 0,
    var size: Int? = 0,
    var views: Int? = 0,
    var link: String? = "",
    var looping: Boolean? = false,
) : Parcelable